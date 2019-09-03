package com.company.View;

import com.company.io.TableReader;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class RatingTable extends JPanel{
    private JTable table;
    public RatingTable() {
        super(new GridLayout(1,0));
        String[] columnNames = {"Player Name", "Time", "Speed"};
        String[][] data = TableReader.TableReader("file/rating.txt");
            table = new JTable(data, columnNames){
                final ImageIcon image = new ImageIcon("res/menu.gif");


                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    final Component c = super.prepareRenderer(renderer, row, column);
                    if (c instanceof JComponent){
                        ((JComponent) c).setOpaque(false);
                    }
                    return c;
                }

                @Override
                public void paint(Graphics g) {
                    //draw image in centre
                    final int imageWidth = image.getIconWidth();
                    final int imageHeight = image.getIconHeight();
                    final Dimension d = getSize();
                    final int x = (d.width - imageWidth)/2;
                    final int y = (d.height - imageHeight)/2;
                    g.drawImage(image.getImage(), x, y, null, null);
                    super.paint(g);
                }
            };
            table.setOpaque(false);
            JFrame jfrm = new JFrame("Rating Table");
            jfrm.getContentPane().setLayout(new FlowLayout());
            jfrm.setSize(900, 315);
            JScrollPane jscrlp = new JScrollPane(table);
            /*jscrlp.setOpaque(true);
            Color backGround=new Color(0,60,0);
            jscrlp.getViewport().setBackground(backGround);*/
            table.setForeground(Color.WHITE);
            table.setFont(new Font("Microsoft JhengHei", Font.BOLD, 13));
            table.setRowHeight(25);

            table.setPreferredScrollableViewportSize(new Dimension(880, 400));
            //Road road=new Road("res/menu.gif", 1200, false);
            jfrm.getContentPane().add(jscrlp);
            jfrm.setVisible(true);
    }

}
