package com.game.tile;

import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.Camera;
import com.game.util.RenderedImage;
import com.game.util.physics.Rect;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class TileManager {
    private static final Stack<Integer[][]> patterns = new Stack<Integer[][]>();
    private static String filePath;
    private static int tileWidth, tileHeight, width, height;
    private final ArrayList<TileMap> maps = new ArrayList<TileMap>();
    private int nextMapX;
    
    
    public TileManager(String mapPath, String filePath) {
        Camera.updateProjection();
        nextMapX = (int) Camera.getProjection()[1].x;
        TileManager.filePath = filePath;
        int layerCount;
        
        try {
            File file = new File(mapPath);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(file.toURI().toString());
            doc.getDocumentElement().normalize();
            
            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            
            Element eElement = (Element) node;
            
            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            AssetPool.addSpriteSheet(filePath, new SpriteSheet(AssetPool.getTexture(filePath), tileWidth, tileHeight));
            
            list = doc.getElementsByTagName("layer");
            layerCount = list.getLength();
            
            for (int i = 0; i < layerCount; i++) {
                node = list.item(i);
                eElement = (Element) node;
                
                if (i <= 0) {
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                }
                
                String data = eElement.getElementsByTagName("data").item(0).getTextContent();
                String[] block = data.split(",");
                
                Integer[][] tmp = new Integer[width][height];
                
                for (int x = 0; x < (width * height); x++) {
                    int temp = Integer.parseInt(block[x].replaceAll("\\s+", ""));
                    tmp[x % width][x / width] = temp;
                    
                }
                
                patterns.push(tmp);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: TileManager " + mapPath + " loading failed");
            e.printStackTrace();
        }
    }
    
    public void removeTilemap(TileMap map) {
        maps.remove(map);
    }
    
    public void update(float dt) {
        Stack<TileMap> del = new Stack<TileMap>();
        for (TileMap map : maps) {
            map.update(dt);
            if (map.getDel()) {
                del.push(map);
            }
        }
        
        for (TileMap x : del) {
            maps.remove(x);
            System.out.println("Removed TileMap");
        }
        
        for (int i = maps.size(); i <= 2; i++) {
            int random = (int) (Math.random() * patterns.size());
            maps.add(new TileMap(filePath, nextMapX, patterns.get(random), tileWidth, tileHeight, width, height));
            nextMapX += width * tileWidth;
            System.out.println("Added Tilemap");
        }
    }
    
    public ArrayList<RenderedImage> getRenderedImage() {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        for (TileMap map : maps) {
            tmp.addAll(map.getRenderedImage());
        }
        
        return tmp;
    }
    
    public Stack<Rect> getRects() {
        Stack<Rect> rect = new Stack<Rect>();
        
        for (TileMap map : maps) {
            rect.addAll(map.getRects());
        }
        
        return rect;
    }
}
