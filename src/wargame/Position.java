package wargame;
import java.io.Serializable;
public class Position implements IConfig, Serializable{
	private int x, y;
	Position(int x, int y) { this.x = x; this.y = y; }
	Position(Position p){this.x = p.x;this.y =p.y; }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) return false; else return true;
	}
	public String toString() { return "("+x+","+y+")"; }
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	public Position pxtoHex(int mx, int my) {
		 /*  SOURCE ADAPTED
		  * Helpful references:
		  * http://www.codeproject.com/Articles/14948/Hexagonal-grid-for-games-and-other-projects-Part-1
		  * http://weblogs.java.net/blog/malenkov/archive/2009/02/hexagonal_tile.html
         * http://www.tonypa.pri.ee/tbw/tut25.html
	 	  * */



			int h= (int) ( IConfig.NB_PIX_CASE * Math.sqrt(3));	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
			int r= (int) (IConfig.NB_PIX_CASE * Math.sqrt(3)/2);	// radius of inscribed circle (centre to middle of each side). r= h/2
			int s= IConfig.NB_PIX_CASE;	// length of one side
			int t= (int) (r / 1.73205);	// short side of 30o triangle outside of each hex

			Position p = new Position(-1, -1);
			mx -= (IConfig.BORDERS+10);
			my -= (int)(IConfig.BORDERS);

			int x = (int) (mx / (s+t)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
			int y = (int) ((my - (x%2)*r)/h); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

			/******FIX for clicking in the triangle spaces (on the left side only)*******/
			//dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
			int dx = mx - x*(s+t);
			int dy = my - y*h;

			if (my - (x%2)*r < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

			//even columns
			if (x%2==0) {
				if (dy > r) {	//bottom half of hexes
					if (dx * r /t < dy - r) {
						x--;
					}
				}
				if (dy < r) {	//top half of hexes
					if ((t - dx)*r/t > dy ) {
						x--;
						y--;
					}
				}
			} else {  // odd columns
				if (dy > h) {	//bottom half of hexes
					if (dx * r/t < dy - h) {
						x--;
						y++;
					}
				}
				if (dy < h) {	//top half of hexes
					if ((t - dx)*r/t > dy - r) {
						x--;
					}
				}
			}
			p.setX(x);
			p.setY(y);
			return p;

		}
}