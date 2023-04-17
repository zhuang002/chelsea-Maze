import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		for (int i=0;i<testCases;i++) {
			doTestcase(sc);
		}
	}

	private static void doTestcase(Scanner sc) {
		// TODO Auto-generated method stub
		int rows = sc.nextInt();
		int cols = sc.nextInt();
		
		sc.nextLine();
		
		char[][] map=new char[rows][cols];
		for (int i=0;i<rows;i++) {
			String line = sc.nextLine();
			for (int j=0;j<line.length();j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(doBFS(map));
	}

	private static int doBFS(char[][] map) {
		// TODO Auto-generated method stub
		if (map.length==1 && map[0].length==1)
			return 1;
		Coord start = new Coord(0,0);
		
		ArrayList<Coord> current = new ArrayList<>();
		current.add(start);
		ArrayList<Coord> next = new ArrayList<>();
		Set<Coord> passed = new HashSet<>();
		int n = 1;
		while (!current.isEmpty()) {
			for (Coord coord:current) {
				ArrayList<Coord> neighbours = getNeighbours(coord, map, passed);
				if (neighbours==null) {
					return n+1;
				}
				next.addAll(neighbours);
			}
			n++;
			current = next;
			next = new ArrayList<>();
		}
		
		return -1;
	}

	private static ArrayList<Coord> getNeighbours(Coord coord, char[][] map, Set<Coord> passed) {
		// TODO Auto-generated method stub
		ArrayList<Coord> neighbours = new ArrayList<>();
		switch(map[coord.row][coord.col]) {
		case '+':
			if (go(coord, map, passed, -1, 0, neighbours)) 
				return null;
			if (go(coord, map, passed, 0, -1, neighbours))
				return null;
			if (go(coord, map, passed, 1, 0, neighbours))
				return null;
			if (go(coord, map, passed, 0, 1, neighbours))
				return null;
			break;
		case '|':
			if (go(coord, map, passed, -1, 0, neighbours)) 
				return null;
			if (go(coord, map, passed, 1, 0, neighbours))
				return null;
			break;
		case '-':
			if (go(coord, map, passed, 0, -1, neighbours))
				return null;
			if (go(coord, map, passed, 0, 1, neighbours))
				return null;
			break;
		default:
			break;
		}
		passed.add(coord);
		return neighbours;
	}

	private static boolean go(Coord coord, char[][] map, Set<Coord> passed, int dr, int dc, ArrayList<Coord> neighbours) {
		// TODO Auto-generated method stub
		int r=coord.row+dr;
		int c=coord.col+dc;
		if (r<0 || r>=map.length || c<0 || c>=map[0].length) 
			return false;
		if (map[r][c] == '*')
			return false;
		if (r==map.length-1 && c==map[0].length-1) {
			return true;
		}
		Coord newCoord = new Coord(r,c);
		if (passed.contains(newCoord)) 
			return false;
		
		neighbours.add(newCoord);
		
		return false;
	}

}

class Coord {
	int row;
	int col;
	
	public Coord(int r, int c) {
		row = r;
		col = c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	
	
	
}
