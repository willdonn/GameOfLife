package Board;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CellPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private CellModel cellModel;
	
	public CellPanel(Position position) {
		this.cellModel = new CellModel(position);
		this.setBackground(Color.BLACK);
	}
	
	public CellModel getModel() {
		return cellModel;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (cellModel.isAlive()) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(1, 1, getWidth()-2, getHeight()-2);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(1, 1, getWidth()-2, getHeight()-2);
		} 
	}


}
