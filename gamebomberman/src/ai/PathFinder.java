package ai;

import java.util.ArrayList;

import main.GamePanel;

public class PathFinder {
	GamePanel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	
	boolean goalReached = false;
	int step = 0;
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		node = new Node[gp.maxScreenCol][gp.maxScreenRow];
		
		int col = 0;
		int row = 0;
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			node[col][row] = new Node(col, row);
			
			col++;
			if( col == gp.maxScreenCol) {
				col =  0;
				row++;
			}
		}
	}
	public void resetNode() {
		int col = 0;
		int row = 0;
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if( col == gp.maxScreenCol) {
				col =  0;
				row++;
			}
		}
		
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	public void setNode( int startCol, int startRow, int goalCol, int goalRow) {
		resetNode();
		
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;
		while( col < gp.maxScreenCol && row < gp.maxScreenRow) {
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row]
		}
	}
}
