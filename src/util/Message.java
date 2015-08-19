package util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Message {

	public static void showError(Object error, Component component) {
		JOptionPane.showMessageDialog(component, error, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void showInfo(Object message, Component component) {
		JOptionPane.showMessageDialog(component, message, "Atención",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static int showConfirm(Object message, Component component) {
		return JOptionPane.showConfirmDialog(component, message,
				"¿Qué desea hacer?", JOptionPane.YES_NO_OPTION);
	}

}
