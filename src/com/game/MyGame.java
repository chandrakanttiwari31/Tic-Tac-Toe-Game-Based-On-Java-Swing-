package com.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class MyGame extends JFrame implements ActionListener {

	JLabel heading, clockLabel;
	Font font = new Font("", Font.BOLD, 40);
	JPanel mainPanel;

	JButton btns[] = new JButton[9];

	// game instance variable;

	int gameChances[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
	int activePlayer = 0;

	int vps[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
			{ 2, 4, 6 } };
	int winner = 2;
	int i;

	boolean gameOver = false;

	MyGame()

	{

		setTitle("my tic tac toe");
		setSize(550, 550);

		ImageIcon icon = new ImageIcon("src/img/icon.png");

		setIconImage(icon.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creategui();
		setVisible(true);
	}

	private void creategui() {

		this.getContentPane().setBackground(Color.decode("#2196f3"));
		this.setLayout(new BorderLayout());

		// north heading

		heading = new JLabel("Tic Tac Toe");
		heading.setIcon(new ImageIcon("src/imp/icon.png"));
		heading.setFont(font);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.white);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);

		this.add(heading, BorderLayout.NORTH);

		clockLabel = new JLabel("Clock");
		clockLabel.setForeground(Color.white);
		clockLabel.setFont(font);
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(clockLabel, BorderLayout.SOUTH);

		/// for set local time

		Thread t = new Thread() {
			public void run() {

				while (true) {

					try {

						String datetime = new Date().toLocaleString();
						clockLabel.setText(datetime);

						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		t.start();

		/// panel section

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 3));

		for (int i = 1; i <= 9; i++) {

			JButton btn = new JButton();
			// btn.setIcon(new ImageIcon("src/img/zero.png"));

			btn.setBackground(Color.BLUE);
			btn.setFont(font);
			mainPanel.add(btn);
			btns[i - 1] = btn;
			btn.addActionListener(this);
			btn.setName(String.valueOf(i - 1));

		}

		this.add(mainPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton currentButton = (JButton) e.getSource();
		String namestr = currentButton.getName();
		int name = Integer.parseInt(namestr.trim());

		if (gameOver == true) {

			JOptionPane.showMessageDialog(this, "Game Already Over");
			return;
		}

		if (gameChances[name] == 2) {

			if (activePlayer == 1) {

				currentButton.setIcon(new ImageIcon("src/img/11.jpg"));

				gameChances[name] = activePlayer;
				activePlayer = 0;
			} else {
				currentButton.setIcon(new ImageIcon("src/img/zero.png"));
				gameChances[name] = activePlayer;

				activePlayer = 1;
			}

			// find winners

			for (int[] temp : vps) {

				if ((gameChances[temp[0]] == gameChances[temp[1]]) && (gameChances[temp[1]] == gameChances[temp[2]])
						&& gameChances[temp[2]] != 2) {

					winner = gameChances[temp[0]];

					gameOver = true;

					JOptionPane.showMessageDialog(null, "player " + winner + " has won the  game");
					i = JOptionPane.showConfirmDialog(this, "Do you want to play again  ??");

					if (i == 0) {

						this.setVisible(false);
						new MyGame();

					}

					else if (i == 1) {
						System.exit(3456);

					} else {

					}

					System.out.println(i);
					break;
				}
			}

			// draw logic

			int counter = 0;
			for (int x : gameChances) {

				if (x == 2) {

					counter++;
					break;
				}
			}

			if (counter == 0 && gameOver == false) {

				JOptionPane.showMessageDialog(null, "Its Draw....");

				int ii = JOptionPane.showConfirmDialog(this, "Play More...??");

				if (ii == 0) {
					this.setVisible(false);
					new MyGame();
				} else if (ii == 1) {

					System.exit(12345);
				}

				else {

				}
				gameOver = true;

			}
		}

		else {
			JOptionPane.showMessageDialog(this, "positions occupied");

		}

	}

}
