package Board;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CellPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private CellModel cellModel;
	
	public CellPanel(Position position) {
		this.cellModel = new CellModel(position);
	}
	
	public CellModel getModel() {
		return cellModel;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//		int centrX = getWidth()/2;
//		int centrY = getWidth()/2;
//		int radius = getWidth()/4;
		
		if (cellModel.isAlive()) {
			g.setColor(Color.YELLOW);
			g.fillRect(1, 1, getWidth()-1, getHeight()-1);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(1, 1, getWidth()-1, getHeight()-1);
		}
		
//		if (contents != null) {
//			g.setColor(contents.getColor());
//			g.fillOval(centrX-radius, centrY-radius, radius*2, radius*2);
//			
//		} 
	}


}
