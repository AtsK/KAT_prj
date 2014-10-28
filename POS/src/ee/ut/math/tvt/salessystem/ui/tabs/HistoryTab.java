package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class HistoryTab { 
    
	
	private SalesSystemModel model;

	 public HistoryTab(SalesSystemModel model) {
	    this.model = model;
	  }

    public Component draw() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 0d;

        panel.add(drawHistoryMenuPane(), gc);

        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryMainPane(), gc);
        return panel;
      }

      
      private Component drawHistoryMenuPane() {
        JPanel panel = new JPanel();

        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();

        panel.setLayout(gb);

        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.weightx = 0;

       

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
      }


      private Component drawHistoryMainPane() {
        JPanel panel = new JPanel();

        JTable table = new JTable(model.getHistoryTableModel());

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        panel.setLayout(gb);
        panel.add(scrollPane, gc);

        panel.setBorder(BorderFactory.createTitledBorder("History"));
        return panel;
      
    }
}