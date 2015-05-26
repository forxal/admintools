package com.anmyst.admintools;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.BevelBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.w3c.dom.Document;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

/**
 * Admin Tools UI 
 */
public class AdminTools {

	private JFrame frmAdminTools;	
	private JTree treeCommands;
	
	private AdminUtils au = new AdminUtils(); //handle utility functions
	private DefaultTreeModel cmdmodel; 
	private JTextField textAddress;
	
	/**
	 * Execute command
	 * @param cmd - Command text
	 * @return 0 - done successfully, not 0 - error
	 */
	private int execCommand(String cmd) {
		try {
			Runtime rt = Runtime.getRuntime();  
			//Process proc = rt.exec(cmd);
			rt.exec(cmd);
		}
		catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	/** 
	 * Show MessageBox
	 * @param title - Message title
	 * @param mess - Message text
	 */
	private void showMessage(String title, String mess) {
		JOptionPane.showMessageDialog(frmAdminTools, mess, title, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTools window = new AdminTools();
					window.frmAdminTools.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminTools() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminTools = new JFrame();
		frmAdminTools.setTitle("Admin Tools");
		frmAdminTools.setBounds(100, 100, 450, 500);
		frmAdminTools.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{201, 10, 10, 10, 0};
		gridBagLayout.rowHeights = new int[]{0, 10, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAdminTools.getContentPane().setLayout(gridBagLayout);
		
		JPanel panelTop = new JPanel();
		panelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelTop = new GridBagConstraints();
		gbc_panelTop.gridwidth = 4;
		gbc_panelTop.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTop.anchor = GridBagConstraints.NORTH;
		gbc_panelTop.insets = new Insets(0, 0, 5, 0);
		gbc_panelTop.gridx = 0;
		gbc_panelTop.gridy = 0;
		frmAdminTools.getContentPane().add(panelTop, gbc_panelTop);
		GridBagLayout gbl_panelTop = new GridBagLayout();
		gbl_panelTop.columnWidths = new int[]{203, 29, 0};
		gbl_panelTop.rowHeights = new int[]{21, 0};
		gbl_panelTop.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelTop.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelTop.setLayout(gbl_panelTop);
		
		JMenuBar menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.BOTH;
		gbc_menuBar.anchor = GridBagConstraints.NORTHWEST;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		panelTop.add(menuBar, gbc_menuBar);
		
		JMenu mnMenuFile = new JMenu("File");
		menuBar.add(mnMenuFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			/** Exit application*/
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		mnMenuFile.add(mntmExit);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelMain = new GridBagConstraints();
		gbc_panelMain.gridheight = 2;
		gbc_panelMain.gridwidth = 4;
		gbc_panelMain.fill = GridBagConstraints.BOTH;
		gbc_panelMain.insets = new Insets(0, 0, 5, 0);
		gbc_panelMain.gridx = 0;
		gbc_panelMain.gridy = 1;
		frmAdminTools.getContentPane().add(panelMain, gbc_panelMain);
		GridBagLayout gbl_panelMain = new GridBagLayout();
		gbl_panelMain.columnWidths = new int[]{0, 0};
		gbl_panelMain.rowHeights = new int[]{0, 0};
		gbl_panelMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelMain.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelMain.setLayout(gbl_panelMain);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panelMain.add(tabbedPane, gbc_tabbedPane);
		
		JPanel panelCommands = new JPanel();
		tabbedPane.addTab("Commands", null, panelCommands, null);
		GridBagLayout gbl_panelCommands = new GridBagLayout();
		gbl_panelCommands.columnWidths = new int[]{0, 0};
		gbl_panelCommands.rowHeights = new int[]{0, 0};
		gbl_panelCommands.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelCommands.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelCommands.setLayout(gbl_panelCommands);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelCommands.add(scrollPane, gbc_scrollPane);
		
		treeCommands = new JTree();
		treeCommands.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("None") {
				{
				}
			}
		));
		treeCommands.addMouseListener(new MouseAdapter() {
			/** Execute selected command */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (arg0.getClickCount() == 2) {
						TreePath tp = treeCommands.getPathForLocation(arg0.getX(), arg0.getY());
						//TreePath tp = treeCommands.getSelectionPath();	
						if (tp != null) {
							DefaultMutableTreeNode tnode = (DefaultMutableTreeNode)tp.getLastPathComponent();
							CommandTreeNode cnode = (CommandTreeNode)tnode.getUserObject();
							if (!cnode.isFolder()){
								execCommand(cnode.getCommand());
								showMessage("Command", "Staring "+cnode.getName());
							}						
						}
					}
				}
				catch (Exception E) {
					
				}
				
			}
			/*
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					//TreePath tp = treeCommands.getPathForLocation(arg0.getX(), arg0.getY());
					TreePath tp = treeCommands.getSelectionPath();
					if (tp != null) {
						String snode = tp.getLastPathComponent().toString();
						if (snode.equalsIgnoreCase("Users&Computers")) {
							execCommand("cmd /C mmc dsa.msc");
							showMessage("Command", "Staring "+snode);
						}
						if (snode.equalsIgnoreCase("DHCP")) {
							execCommand("cmd /C mmc dhcpmgmt.msc");
							showMessage("Command", "Staring "+snode);
						}
						if (snode.equalsIgnoreCase("Computer Management")) {
							execCommand("cmd /C mmc compmgmt.msc");
							showMessage("Command", "Staring "+snode);
						}											
						if (snode.equalsIgnoreCase("Uninstall Software")) {
							execCommand("cmd /C control appwiz.cpl");
							showMessage("Command", "Staring "+snode);
						}
						if (snode.equalsIgnoreCase("Printers")) {
							execCommand("cmd /C control printers");
							showMessage("Command", "Staring "+snode);
						}
					}
				}
				
			}
			*/
		});
		
		//String path = new File(".").getAbsolutePath();
		
		cmdmodel = new DefaultTreeModel(new DefaultMutableTreeNode(new CommandTreeNode("root", "none", true)));
		//Document doc = au.loadDocument(path+"\\commands.xml");
		Document doc = au.loadDocument("commands.xml");
		if (doc != null) {
			au.fillTree(cmdmodel, doc);
			treeCommands.setModel(cmdmodel);
		}
		/*		
		treeCommands.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Admin Tools") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Active Directory");
						node_1.add(new DefaultMutableTreeNode("Users&Computers"));
						node_1.add(new DefaultMutableTreeNode("DHCP"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Local PC");
						node_1.add(new DefaultMutableTreeNode("Computer Management"));
						node_1.add(new DefaultMutableTreeNode("Uninstall Software"));
						node_1.add(new DefaultMutableTreeNode("Printers"));
					add(node_1);
				}
			}
		));
		*/
		scrollPane.setViewportView(treeCommands);
		
		JPanel panelBottom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBottom.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelBottom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelBottom = new GridBagConstraints();
		gbc_panelBottom.insets = new Insets(0, 0, 5, 0);
		gbc_panelBottom.gridwidth = 4;
		gbc_panelBottom.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBottom.anchor = GridBagConstraints.NORTH;
		gbc_panelBottom.gridx = 0;
		gbc_panelBottom.gridy = 3;
		frmAdminTools.getContentPane().add(panelBottom, gbc_panelBottom);
		
		JLabel lblRemoteAssistancewin = new JLabel("Remote Assistance (Win7)");
		lblRemoteAssistancewin.setHorizontalAlignment(SwingConstants.LEFT);
		panelBottom.add(lblRemoteAssistancewin);
		
		textAddress = new JTextField();
		textAddress.setHorizontalAlignment(SwingConstants.LEFT);
		panelBottom.add(textAddress);
		textAddress.setColumns(15);
		
		JButton buttonGo = new JButton("Go");
		buttonGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				execCommand("cmd /C msra /offerra "+textAddress.getText());
			}
		});
		panelBottom.add(buttonGo);
		
		JPanel panelVersion = new JPanel();
		panelVersion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelVersion = new GridBagConstraints();
		gbc_panelVersion.anchor = GridBagConstraints.NORTH;
		gbc_panelVersion.gridwidth = 4;
		gbc_panelVersion.insets = new Insets(0, 0, 0, 5);
		gbc_panelVersion.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelVersion.gridx = 0;
		gbc_panelVersion.gridy = 4;
		frmAdminTools.getContentPane().add(panelVersion, gbc_panelVersion);
		
		JLabel lblVersion = new JLabel("Admin Tools version 1.0.2");
		panelVersion.add(lblVersion);
	}

}
