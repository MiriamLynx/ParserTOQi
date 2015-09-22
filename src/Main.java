import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jvnet.substance.SubstanceLookAndFeel;

import util.Message;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Main extends JFrame {

	private static final long serialVersionUID = -7652472277425949850L;
	private JPanel contentPane;
	private JTextField textFile;
	private JTextField saveFile;
	private StringBuilder builder;
	private Map<String, List<String>> map;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					SubstanceLookAndFeel
							.setSkin("org.jvnet.substance.skin.CremeSkin");
					Main frame = new Main();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new java.awt.Color(169, 208, 245));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(26, 24, 387, 104);
		panel.setBackground(new java.awt.Color(224, 242, 247));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblArchivoPdf = new JLabel("Guardar en:");
		lblArchivoPdf.setBounds(10, 43, 65, 14);
		panel.add(lblArchivoPdf);

		textFile = new JTextField();
		textFile.setBounds(85, 12, 167, 20);
		panel.add(textFile);
		textFile.setColumns(10);

		JButton btnExaminar = new JButton("Examinar...");
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System
						.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(btnExaminar.getParent());
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String file = selectedFile.getAbsolutePath();
					textFile.setText(file);
				}
			}
		});
		btnExaminar.setBounds(262, 11, 99, 23);
		panel.add(btnExaminar);

		JButton btnNewButton = new JButton("Examinar...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System
						.getProperty("user.home")));
				int result = fileChooser.showSaveDialog(btnExaminar.getParent());
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String file = selectedFile.getAbsolutePath();
					saveFile.setText(file);
				}
			}
		});
		btnNewButton.setBounds(262, 39, 99, 23);
		panel.add(btnNewButton);

		JButton btnGenerarDoc = new JButton("Generar doc");
		btnGenerarDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFile.getText().equals("")
						|| saveFile.getText().equals("")) {
					Message.showError(
							"Los dos campos 'archivo' y 'destino' no pueden estar vacíos.",
							btnGenerarDoc);
				} else {
					try {
						map = new HashMap<String, List<String>>();
						map.put("PUBLICACIÓN", new ArrayList<String>());
						map.put("PUBLICACIÓN NO INDEXADA",
								new ArrayList<String>());
						map.put("LIBRO", new ArrayList<String>());
						map.put("CONGRESO", new ArrayList<String>());
						map.put("CONFERENCIA", new ArrayList<String>());
						map.put("OTROS CALIDAD", new ArrayList<String>());
						map.put("OTROS DOCENTE", new ArrayList<String>());
						map.put("OTROS INVESTIGACIÓN", new ArrayList<String>());
						map.put("PROYECTO", new ArrayList<String>());
						map.put("PROYECTO INNOVACIÓN", new ArrayList<String>());
						map.put("PATENTES Y PRODUCTOS", new ArrayList<String>());
						map.put("OTROS CALIDAD ACTIVIDAD",
								new ArrayList<String>());
						map.put("DIRECCIÓN PROYECTO", new ArrayList<String>());
						map.put("MATERIAL ORIGINAL", new ArrayList<String>());
						map.put("ESTANCIA", new ArrayList<String>());
						map.put("PUESTO", new ArrayList<String>());
						map.put("MATERIAL DOCENTE", new ArrayList<String>());
						map.put("CURSO", new ArrayList<String>());
						map.put("OTROS NÚMERO", new ArrayList<String>());
						map.put("TRANSFERENCIA", new ArrayList<String>());
						map.put("EVALUACIONES POSITIVAS",
								new ArrayList<String>());
						map.put("OTROS TRANSFERENCIA", new ArrayList<String>());
						map.put("TESIS DOCTORALES", new ArrayList<String>());
						map.put("PARTICIPACIÓN PONENTE",
								new ArrayList<String>());
						map.put("PARTICIPACIÓN ASISTENTE",
								new ArrayList<String>());
						map.put("ESTANCIA DOCENTE", new ArrayList<String>());
						map.put("OTROS FORMACIÓN", new ArrayList<String>());
						map.put("PUESTOS DEDICACIÓN", new ArrayList<String>());
						map.put("EVALUACIONES ACTIVIDAD",
								new ArrayList<String>());
						map.put("OTROS MÉRITOS", new ArrayList<String>());
						map.put("TITULACIÓN UNIVERSITARIA",
								new ArrayList<String>());
						map.put("TESIS DOCTORAL", new ArrayList<String>());
						map.put("OTROS TÍTULOS", new ArrayList<String>());
						map.put("BECAS", new ArrayList<String>());
						map.put("PREMIOS", new ArrayList<String>());
						map.put("OTROS PREDOCTORAL", new ArrayList<String>());
						map.put("OTROS POSTDOCTORAL", new ArrayList<String>());
						map.put("OTROS ACADÉMICA", new ArrayList<String>());
						map.put("OTROS EXPERIENCIA", new ArrayList<String>());
						map.put("PUESTO ADMINISTRACIÓN",
								new ArrayList<String>());
						map.put("EXPERIENCIA GESTIÓN", new ArrayList<String>());

						int[] index;
						int nextPage;
						int nexLine;

						PdfReader reader = new PdfReader(textFile.getText());
						int pages = reader.getNumberOfPages();
						index = do1A1(reader, 1, pages);
						nextPage = index[0];
						nexLine = index[1];
						index = do1A2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1A3(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1A5(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1A6(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1A7(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1B1(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1B2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1C2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1C3(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1D1(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do1D2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2A1(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2A2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2A3(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2A4(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2B1(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2B2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2B3(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2B4(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2C1(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2C2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2C3(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2C4(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2D1(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2D2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do2E(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do3A1(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do3A2(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do3A3(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do3A4(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do3A5(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do3A6(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do3A7(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do4A(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do4B(reader, nextPage, pages, nexLine);
						nextPage = index[0];
						nexLine = index[1];
						index = do4C(reader, nextPage, pages, nexLine);
						doWord();
					} catch (IOException e) {
						Message.showError("HORROR", btnGenerarDoc);
					}
				}
			}
		});
		btnGenerarDoc.setBounds(262, 70, 99, 23);
		panel.add(btnGenerarDoc);

		JLabel label = new JLabel("Archivo pdf:");
		label.setBounds(10, 15, 65, 14);
		panel.add(label);

		saveFile = new JTextField();
		saveFile.setBounds(85, 40, 167, 20);
		panel.add(saveFile);
		saveFile.setColumns(10);
		getRootPane().setDefaultButton(btnGenerarDoc);
	}

	private void doWord() throws FileNotFoundException {
		// Blank Document
		XWPFDocument document = new XWPFDocument();
		// create paragraph
		XWPFParagraph paragraph = document.createParagraph();

		XWPFRun publicationsTitle = paragraph.createRun();
		publicationsTitle.setBold(true);
		publicationsTitle.setFontSize(18);
		publicationsTitle.setText("RELACIÓN DE DOCUMENTOS APORTADOS");
		publicationsTitle.addBreak();
		publicationsTitle.addBreak();

		XWPFRun publicationsTitle1 = paragraph.createRun();
		publicationsTitle1.setBold(true);
		publicationsTitle1.setFontSize(16);
		publicationsTitle1.setText("1. ACTIVIDAD INVESTIGADORA.");
		publicationsTitle1.addBreak();
		publicationsTitle1.addBreak();

		XWPFRun publicationsTitle1A = paragraph.createRun();
		publicationsTitle1A.setBold(true);
		publicationsTitle1A.setFontSize(12);
		publicationsTitle1A
				.setText("1.A. CALIDAD Y DIFUSIÓN DE RESULTADOS DE LA ACTIVIDAD INVESTIGADORA");
		publicationsTitle1A.addBreak();
		publicationsTitle1A.addBreak();

		XWPFRun publicationsTitle1A1 = paragraph.createRun();
		publicationsTitle1A1.setBold(true);
		publicationsTitle1A1.setFontSize(10);
		publicationsTitle1A1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A1
				.setText("1.A.1. PUBLICACIONES CIENTÍFICAS INDEXADAS DE ACUERDO CON UN ÍNDICE DE CALIDAD RELATIVO");
		publicationsTitle1A1.addBreak();

		XWPFRun publication = paragraph.createRun();
		for (String s : map.get("PUBLICACIÓN")) {
			publication.setFontSize(9);
			publication.setText(s);
			publication.addBreak();
		}
		publication.addBreak();

		XWPFRun publicationsTitle1A2 = paragraph.createRun();
		publicationsTitle1A2.setBold(true);
		publicationsTitle1A2.setFontSize(10);
		publicationsTitle1A2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A2
				.setText("1.A.2. PUBLICACIONES CIENTÍFICAS NO INDEXADAS DE ACUERDO CON UN ÍNDICE DE CALIDAD RELATIVO");
		publicationsTitle1A2.addBreak();

		XWPFRun notIndexedPublication = paragraph.createRun();
		for (String s : map.get("PUBLICACIÓN NO INDEXADA")) {
			notIndexedPublication.setFontSize(9);
			notIndexedPublication.setText(s);
			notIndexedPublication.addBreak();
		}
		notIndexedPublication.addBreak();

		XWPFRun publicationsTitle1A3 = paragraph.createRun();
		publicationsTitle1A3.setBold(true);
		publicationsTitle1A3.setFontSize(10);
		publicationsTitle1A3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A3.setText("1.A.3. LIBROS Y CAPÍTULOS DE LIBROS");
		publicationsTitle1A3.addBreak();

		XWPFRun libro = paragraph.createRun();
		for (String s : map.get("LIBRO")) {
			libro.setFontSize(9);
			libro.setText(s);
			libro.addBreak();
		}
		libro.addBreak();

		XWPFRun publicationsTitle1A4 = paragraph.createRun();
		publicationsTitle1A4.setBold(true);
		publicationsTitle1A4.setFontSize(10);
		publicationsTitle1A4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A4
				.setText("1.A.4. CREACIONES ARTÍSTICAS Y PROFESIONALES");
		publicationsTitle1A4.addBreak();

		XWPFRun aviso = paragraph.createRun();
		aviso.setFontSize(9);
		aviso.setColor("FF0000");
		aviso.setBold(true);
		aviso.setText("ACUERDATE DE RELLENAR ESTO SI LO HAY");
		aviso.addBreak();
		aviso.addBreak();

		XWPFRun publicationsTitle1A5 = paragraph.createRun();
		publicationsTitle1A5.setBold(true);
		publicationsTitle1A5.setFontSize(10);
		publicationsTitle1A5.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A5.setText("1.A.5. CONGRESOS");
		publicationsTitle1A5.addBreak();

		XWPFRun congress = paragraph.createRun();
		for (String s : map.get("CONGRESO")) {
			congress.setFontSize(9);
			congress.setText(s);
			congress.addBreak();
		}
		congress.addBreak();

		XWPFRun publicationsTitle1A6 = paragraph.createRun();
		publicationsTitle1A6.setBold(true);
		publicationsTitle1A6.setFontSize(10);
		publicationsTitle1A6.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A6.setText("1.A.6. CONFERENCIAS Y SEMINARIOS");
		publicationsTitle1A6.addBreak();

		XWPFRun conference = paragraph.createRun();
		for (String s : map.get("CONFERENCIA")) {
			conference.setFontSize(9);
			conference.setText(s);
			conference.addBreak();
		}
		conference.addBreak();

		XWPFRun publicationsTitle1A7 = paragraph.createRun();
		publicationsTitle1A7.setBold(true);
		publicationsTitle1A7.setFontSize(10);
		publicationsTitle1A7.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A7
				.setText("1.A.7. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD Y DIFUSIÓN DE RESULTADOS DE LA ACTIVIDAD INVESTIGADORA");
		publicationsTitle1A7.addBreak();

		XWPFRun otherQuality = paragraph.createRun();
		for (String s : map.get("OTROS CALIDAD")) {
			otherQuality.setFontSize(9);
			otherQuality.setText(s);
			otherQuality.addBreak();
		}
		otherQuality.addBreak();

		XWPFRun publicationsTitle1B = paragraph.createRun();
		publicationsTitle1B.setBold(true);
		publicationsTitle1B.setFontSize(12);
		publicationsTitle1B
				.setText("1.B. CALIDAD Y NÚMERO DE PROYECTOS Y CONTRATOS DE INVESTIGACIÓN");
		publicationsTitle1B.addBreak();
		publicationsTitle1B.addBreak();

		XWPFRun publicationsTitle1B1 = paragraph.createRun();
		publicationsTitle1B1.setBold(true);
		publicationsTitle1B1.setFontSize(10);
		publicationsTitle1B1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1B1
				.setText("1.B.1. PARTICIPACIÓN EN PROYECTOS DE INVESTIGACIÓN Y/O EN CONTRATOS DE INVESTIGACIÓN");
		publicationsTitle1B1.addBreak();

		XWPFRun project = paragraph.createRun();
		for (String s : map.get("PROYECTO")) {
			project.setFontSize(9);
			project.setText(s);
			project.addBreak();
		}
		project.addBreak();

		XWPFRun publicationsTitle1B2 = paragraph.createRun();
		publicationsTitle1B2.setBold(true);
		publicationsTitle1B2.setFontSize(10);
		publicationsTitle1B2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1B2
				.setText("1.B.2. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD Y NÚMERO DE PROYECTOS Y CONTRATOS DE INVESTIGACIÓN");
		publicationsTitle1B2.addBreak();

		XWPFRun otherNumber = paragraph.createRun();
		for (String s : map.get("OTROS NÚMERO")) {
			otherNumber.setFontSize(9);
			otherNumber.setText(s);
			otherNumber.addBreak();
		}
		otherNumber.addBreak();

		XWPFRun publicationsTitle1C = paragraph.createRun();
		publicationsTitle1C.setBold(true);
		publicationsTitle1C.setFontSize(12);
		publicationsTitle1C
				.setText("1.C. CALIDAD DE LA TRANSFERENCIA DE LOS RESULTADOS");
		publicationsTitle1C.addBreak();
		publicationsTitle1C.addBreak();

		XWPFRun publicationsTitle1C1 = paragraph.createRun();
		publicationsTitle1C1.setBold(true);
		publicationsTitle1C1.setFontSize(10);
		publicationsTitle1C1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1C1
				.setText("1.C.1. PATENTES Y PRODUCTOS CON REGISTRO DE PROPIEDAD INTELECTUAL");
		publicationsTitle1C1.addBreak();

		XWPFRun avisoPatentes = paragraph.createRun();
		avisoPatentes.setFontSize(9);
		avisoPatentes.setColor("FF0000");
		avisoPatentes.setBold(true);
		avisoPatentes.setText("ACUERDATE DE RELLENAR ESTO SI LO HAY");
		avisoPatentes.addBreak();
		avisoPatentes.addBreak();

		XWPFRun publicationsTitle1C2 = paragraph.createRun();
		publicationsTitle1C2.setBold(true);
		publicationsTitle1C2.setFontSize(10);
		publicationsTitle1C2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1C2
				.setText("1.C.2. TRANSFERENCIA DE CONOCIMIENTO AL SECTOR PRODUCTIVO.");
		publicationsTitle1C2.addBreak();

		XWPFRun transference = paragraph.createRun();
		for (String s : map.get("TRANSFERENCIA")) {
			transference.setFontSize(9);
			transference.setText(s);
			transference.addBreak();
		}
		transference.addBreak();

		XWPFRun publicationsTitle1C3 = paragraph.createRun();
		publicationsTitle1C3.setBold(true);
		publicationsTitle1C3.setFontSize(10);
		publicationsTitle1C3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1C3
				.setText("1.C.3. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD DE LA TRANSFERENCIA DE LOS RESULTADOS");
		publicationsTitle1C3.addBreak();

		XWPFRun otherTransference = paragraph.createRun();
		for (String s : map.get("OTROS TRANSFERENCIA")) {
			otherTransference.setFontSize(9);
			otherTransference.setText(s);
			otherTransference.addBreak();
		}
		otherTransference.addBreak();

		XWPFRun publicationsTitle1D = paragraph.createRun();
		publicationsTitle1D.setBold(true);
		publicationsTitle1D.setFontSize(12);
		publicationsTitle1D.setText("1.D. MOVILIDAD DEL PROFESORADO");
		publicationsTitle1D.addBreak();
		publicationsTitle1D.addBreak();

		XWPFRun publicationsTitle1D1 = paragraph.createRun();
		publicationsTitle1D1.setBold(true);
		publicationsTitle1D1.setFontSize(10);
		publicationsTitle1D1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1D1
				.setText("1.D.1. ESTANCIAS EN CENTROS DE INVESTIGACIÓN");
		publicationsTitle1D1.addBreak();

		XWPFRun stay = paragraph.createRun();
		for (String s : map.get("ESTANCIA")) {
			stay.setFontSize(9);
			stay.setText(s);
			stay.addBreak();
		}
		stay.addBreak();

		XWPFRun publicationsTitle1D2 = paragraph.createRun();
		publicationsTitle1D2.setBold(true);
		publicationsTitle1D2.setFontSize(10);
		publicationsTitle1D2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1D2
				.setText("1.D.2. OTROS MÉRITOS RELACIONADOS CON LA MOVILIDAD DEL PROFESORADO");
		publicationsTitle1D2.addBreak();

		XWPFRun publicationsTitle1E = paragraph.createRun();
		publicationsTitle1E.setBold(true);
		publicationsTitle1E.setFontSize(12);
		publicationsTitle1E
				.setText("1.E. OTROS MÉRITOS RELACIONADOS CON LA ACTIVIDAD INVESTIGADORA");
		publicationsTitle1E.addBreak();
		publicationsTitle1E.addBreak();

		XWPFRun othersInvestigation = paragraph.createRun();
		for (String s : map.get("OTROS INVESTIGACIÓN")) {
			othersInvestigation.setFontSize(9);
			othersInvestigation.setText(s);
			othersInvestigation.addBreak();
		}
		othersInvestigation.addBreak();

		XWPFRun publicationsTitle2 = paragraph.createRun();
		publicationsTitle2.setBold(true);
		publicationsTitle2.setFontSize(16);
		publicationsTitle2.setText("2. ACTIVIDAD DOCENTE O PROFESIONAL.");
		publicationsTitle2.addBreak();
		publicationsTitle2.addBreak();

		XWPFRun publicationsTitle2A = paragraph.createRun();
		publicationsTitle2A.setBold(true);
		publicationsTitle2A.setFontSize(12);
		publicationsTitle2A.setText("2.A. DEDICACIÓN DOCENTE");
		publicationsTitle2A.addBreak();
		publicationsTitle2A.addBreak();

		XWPFRun publicationsTitle2A1 = paragraph.createRun();
		publicationsTitle2A1.setBold(true);
		publicationsTitle2A1.setFontSize(10);
		publicationsTitle2A1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A1.setText("2.A.1. PUESTOS DOCENTES OCUPADOS");
		publicationsTitle2A1.addBreak();

		XWPFRun position = paragraph.createRun();
		for (String s : map.get("PUESTO")) {
			position.setFontSize(9);
			position.setText(s);
			position.addBreak();
		}
		position.addBreak();

		XWPFRun publicationsTitle2A2 = paragraph.createRun();
		publicationsTitle2A2.setBold(true);
		publicationsTitle2A2.setFontSize(10);
		publicationsTitle2A2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A2.setText("2.A.2. DIRECCIÓN DE TESIS DOCTORALES");
		publicationsTitle2A2.addBreak();

		XWPFRun tesis = paragraph.createRun();
		for (String s : map.get("TESIS DOCTORALES")) {
			tesis.setFontSize(9);
			tesis.setText(s);
			tesis.addBreak();
		}
		tesis.addBreak();

		XWPFRun publicationsTitle2A3 = paragraph.createRun();
		publicationsTitle2A3.setBold(true);
		publicationsTitle2A3.setFontSize(10);
		publicationsTitle2A3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A3
				.setText("2.A.3. DIRECCIÓN DE PROYECTOS FIN DE CARRERA, TESINAS, TRABAJOS FIN DE MÁSTER, ETC.");
		publicationsTitle2A3.addBreak();

		XWPFRun projectDirection = paragraph.createRun();
		for (String s : map.get("DIRECCIÓN PROYECTO")) {
			projectDirection.setFontSize(9);
			projectDirection.setText(s);
			projectDirection.addBreak();
		}
		projectDirection.addBreak();

		XWPFRun publicationsTitle2A4 = paragraph.createRun();
		publicationsTitle2A4.setBold(true);
		publicationsTitle2A4.setFontSize(10);
		publicationsTitle2A4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A4
				.setText("2.A.4. OTROS MÉRITOS RELACIONADOS CON LA ACTIVIDAD DOCENTE");
		publicationsTitle2A4.addBreak();

		XWPFRun othersDocent = paragraph.createRun();
		for (String s : map.get("OTROS DOCENTE")) {
			othersDocent.setFontSize(9);
			othersDocent.setText(s);
			othersDocent.addBreak();
		}
		othersDocent.addBreak();

		XWPFRun publicationsTitle2B = paragraph.createRun();
		publicationsTitle2B.setBold(true);
		publicationsTitle2B.setFontSize(12);
		publicationsTitle2B.setText("2.B. CALIDAD DE LA ACTIVIDAD DOCENTE");
		publicationsTitle2B.addBreak();
		publicationsTitle2B.addBreak();

		XWPFRun publicationsTitle2B1 = paragraph.createRun();
		publicationsTitle2B1.setBold(true);
		publicationsTitle2B1.setFontSize(10);
		publicationsTitle2B1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B1
				.setText("2.B.1. EVALUACIONES POSITIVAS DE SU ACTIVIDAD");
		publicationsTitle2B1.addBreak();

		XWPFRun positiveEvaluations = paragraph.createRun();
		for (String s : map.get("EVALUACIONES POSITIVAS")) {
			positiveEvaluations.setFontSize(9);
			positiveEvaluations.setText(s);
			positiveEvaluations.addBreak();
		}
		positiveEvaluations.addBreak();

		XWPFRun publicationsTitle2B2 = paragraph.createRun();
		publicationsTitle2B2.setBold(true);
		publicationsTitle2B2.setFontSize(10);
		publicationsTitle2B2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B2
				.setText("2.B.2. MATERIAL DOCENTE ORIGINAL Y PUBLICACIONES DOCENTES");
		publicationsTitle2B2.addBreak();

		XWPFRun originalMaterial = paragraph.createRun();
		for (String s : map.get("MATERIAL ORIGINAL")) {
			originalMaterial.setFontSize(9);
			originalMaterial.setText(s);
			originalMaterial.addBreak();
		}
		originalMaterial.addBreak();

		XWPFRun publicationsTitle2B3 = paragraph.createRun();
		publicationsTitle2B3.setBold(true);
		publicationsTitle2B3.setFontSize(10);
		publicationsTitle2B3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B3.setText("2.B.3. PROYECTOS DE INNOVACIÓN DOCENTE");
		publicationsTitle2B3.addBreak();

		XWPFRun innovationProject = paragraph.createRun();
		for (String s : map.get("PROYECTO INNOVACIÓN")) {
			innovationProject.setFontSize(9);
			innovationProject.setText(s);
			innovationProject.addBreak();
		}
		innovationProject.addBreak();

		XWPFRun publicationsTitle2B4 = paragraph.createRun();
		publicationsTitle2B4.setBold(true);
		publicationsTitle2B4.setFontSize(10);
		publicationsTitle2B4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B4
				.setText("2.B.4. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD DE LA ACTIVIDAD DOCENTE");
		publicationsTitle2B4.addBreak();

		XWPFRun othersQualityActivity = paragraph.createRun();
		for (String s : map.get("OTROS CALIDAD ACTIVIDAD")) {
			othersQualityActivity.setFontSize(9);
			othersQualityActivity.setText(s);
			othersQualityActivity.addBreak();
		}
		othersQualityActivity.addBreak();

		XWPFRun publicationsTitle2C = paragraph.createRun();
		publicationsTitle2C.setBold(true);
		publicationsTitle2C.setFontSize(12);
		publicationsTitle2C.setText("2.C CALIDAD DE LA FORMACIÓN DOCENTE");
		publicationsTitle2C.addBreak();
		publicationsTitle2C.addBreak();

		XWPFRun publicationsTitle2C1 = paragraph.createRun();
		publicationsTitle2C1.setBold(true);
		publicationsTitle2C1.setFontSize(10);
		publicationsTitle2C1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C1
				.setText("2.C.1. PARTICIPACIÓN, COMO PONENTE, EN CONGRESOS ORIENTADOS A LA FORMACIÓN DOCENTE UNIVERSITARIA");
		publicationsTitle2C1.addBreak();

		XWPFRun ponentParticipation = paragraph.createRun();
		for (String s : map.get("PARTICIPACIÓN PONENTE")) {
			ponentParticipation.setFontSize(9);
			ponentParticipation.setText(s);
			ponentParticipation.addBreak();
		}
		ponentParticipation.addBreak();

		XWPFRun publicationsTitle2C2 = paragraph.createRun();
		publicationsTitle2C2.setBold(true);
		publicationsTitle2C2.setFontSize(10);
		publicationsTitle2C2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C2
				.setText("2.C.2. PARTICIPACIÓN, COMO ASISTENTE, EN CONGRESOS ORIENTADOS A LA FORMACIÓN DOCENTE UNIVERSITARIA");
		publicationsTitle2C2.addBreak();

		XWPFRun assistantParticipation = paragraph.createRun();
		for (String s : map.get("PARTICIPACIÓN ASISTENTE")) {
			assistantParticipation.setFontSize(9);
			assistantParticipation.setText(s);
			assistantParticipation.addBreak();
		}
		assistantParticipation.addBreak();

		XWPFRun publicationsTitle2C3 = paragraph.createRun();
		publicationsTitle2C3.setBold(true);
		publicationsTitle2C3.setFontSize(10);
		publicationsTitle2C3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C3.setText("2.C.3. ESTANCIAS EN CENTROS DOCENTES");
		publicationsTitle2C3.addBreak();

		XWPFRun docentStay = paragraph.createRun();
		for (String s : map.get("ESTANCIA DOCENTE")) {
			docentStay.setFontSize(9);
			docentStay.setText(s);
			docentStay.addBreak();
		}
		docentStay.addBreak();

		XWPFRun publicationsTitle2C4 = paragraph.createRun();
		publicationsTitle2C4.setBold(true);
		publicationsTitle2C4.setFontSize(10);
		publicationsTitle2C4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C4
				.setText("2.C.4.OTROS MÉRITOS RELACIONADOS CON LA CALIDAD DE LA FORMACIÓN");
		publicationsTitle2C4.addBreak();

		XWPFRun othersFormation = paragraph.createRun();
		for (String s : map.get("OTROS FORMACIÓN")) {
			othersFormation.setFontSize(9);
			othersFormation.setText(s);
			othersFormation.addBreak();
		}
		othersFormation.addBreak();

		XWPFRun publicationsTitle2D = paragraph.createRun();
		publicationsTitle2D.setBold(true);
		publicationsTitle2D.setFontSize(12);
		publicationsTitle2D
				.setText("2.D CALIDAD Y DEDICACIÓN A ACTIVIDADES PROFESIONALES,EN EMPRESAS, INSTITUCIONES, ORGANISMOS PÚBLICOS DE INVESTIGACIÓN U HOSPITALES, DISTINTAS A LAS DOCENTES O INVESTIGADORAS");
		publicationsTitle2D.addBreak();
		publicationsTitle2D.addBreak();

		XWPFRun publicationsTitle2D1 = paragraph.createRun();
		publicationsTitle2D1.setBold(true);
		publicationsTitle2D1.setFontSize(10);
		publicationsTitle2D1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2D1.setText("2.D.1. PUESTOS OCUPADOS Y DEDICACIÓN");
		publicationsTitle2D1.addBreak();

		XWPFRun positionDedication = paragraph.createRun();
		for (String s : map.get("PUESTOS DEDICACIÓN")) {
			positionDedication.setFontSize(9);
			positionDedication.setText(s);
			positionDedication.addBreak();
		}
		positionDedication.addBreak();

		XWPFRun publicationsTitle2D2 = paragraph.createRun();
		publicationsTitle2D2.setBold(true);
		publicationsTitle2D2.setFontSize(10);
		publicationsTitle2D2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2D2
				.setText("2.D.2. EVALUACIONES POSITIVAS DE SU ACTIVIDAD");
		publicationsTitle2D2.addBreak();

		XWPFRun evaluationActivity = paragraph.createRun();
		for (String s : map.get("EVALUACIONES ACTIVIDAD")) {
			evaluationActivity.setFontSize(9);
			evaluationActivity.setText(s);
			evaluationActivity.addBreak();
		}
		evaluationActivity.addBreak();

		XWPFRun publicationsTitle2E = paragraph.createRun();
		publicationsTitle2E.setBold(true);
		publicationsTitle2E.setFontSize(12);
		publicationsTitle2E
				.setText("2.E. OTROS MÉRITOS RELACIONADOS CON LA ACTIVIDAD PROFESIONAL");
		publicationsTitle2E.addBreak();
		publicationsTitle2E.addBreak();

		XWPFRun others = paragraph.createRun();
		for (String s : map.get("OTROS MÉRITOS")) {
			others.setFontSize(9);
			others.setText(s);
			others.addBreak();
		}
		others.addBreak();

		XWPFRun publicationsTitle3 = paragraph.createRun();
		publicationsTitle3.setBold(true);
		publicationsTitle3.setFontSize(16);
		publicationsTitle3.setText("3. FORMACIÓN ACADÉMICA");
		publicationsTitle3.addBreak();
		publicationsTitle3.addBreak();

		XWPFRun publicationsTitle3A = paragraph.createRun();
		publicationsTitle3A.setBold(true);
		publicationsTitle3A.setFontSize(12);
		publicationsTitle3A.setText("3.A. CALIDAD DE LA FORMACIÓN");
		publicationsTitle3A.addBreak();
		publicationsTitle3A.addBreak();

		XWPFRun publicationsTitle3A1 = paragraph.createRun();
		publicationsTitle3A1.setBold(true);
		publicationsTitle3A1.setFontSize(10);
		publicationsTitle3A1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A1.setText("3.A.1. TITULACIÓN UNIVERSITARIA");
		publicationsTitle3A1.addBreak();

		XWPFRun titulation = paragraph.createRun();
		for (String s : map.get("TITULACIÓN UNIVERSITARIA")) {
			titulation.setFontSize(9);
			titulation.setText(s);
			titulation.addBreak();
		}
		titulation.addBreak();

		XWPFRun publicationsTitle3A2 = paragraph.createRun();
		publicationsTitle3A2.setBold(true);
		publicationsTitle3A2.setFontSize(10);
		publicationsTitle3A2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A2.setText("3.A.2. TESIS DOCTORAL");
		publicationsTitle3A2.addBreak();

		XWPFRun tesisDoctoral = paragraph.createRun();
		for (String s : map.get("TESIS DOCTORAL")) {
			tesisDoctoral.setFontSize(9);
			tesisDoctoral.setText(s);
			tesisDoctoral.addBreak();
		}
		tesisDoctoral.addBreak();

		XWPFRun publicationsTitle3A3 = paragraph.createRun();
		publicationsTitle3A3.setBold(true);
		publicationsTitle3A3.setFontSize(10);
		publicationsTitle3A3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A3.setText("3.A.3. OTROS TÍTULOS");
		publicationsTitle3A3.addBreak();

		XWPFRun otherTitulation = paragraph.createRun();
		for (String s : map.get("OTROS TÍTULOS")) {
			otherTitulation.setFontSize(9);
			otherTitulation.setText(s);
			otherTitulation.addBreak();
		}
		otherTitulation.addBreak();

		XWPFRun publicationsTitle3A4 = paragraph.createRun();
		publicationsTitle3A4.setBold(true);
		publicationsTitle3A4.setFontSize(10);
		publicationsTitle3A4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A4.setText("3.A.4. BECAS Y AYUDAS");
		publicationsTitle3A4.addBreak();

		XWPFRun schollarship = paragraph.createRun();
		for (String s : map.get("BECAS")) {
			schollarship.setFontSize(9);
			schollarship.setText(s);
			schollarship.addBreak();
		}
		schollarship.addBreak();

		XWPFRun publicationsTitle3A5 = paragraph.createRun();
		publicationsTitle3A5.setBold(true);
		publicationsTitle3A5.setFontSize(10);
		publicationsTitle3A5.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A5.setText("3.A.5. PREMIOS");
		publicationsTitle3A5.addBreak();

		XWPFRun award = paragraph.createRun();
		for (String s : map.get("PREMIOS")) {
			award.setFontSize(9);
			award.setText(s);
			award.addBreak();
		}
		award.addBreak();

		XWPFRun publicationsTitle3A6 = paragraph.createRun();
		publicationsTitle3A6.setBold(true);
		publicationsTitle3A6.setFontSize(10);
		publicationsTitle3A6.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A6
				.setText("3.A.6. OTROS MÉRITOS ASOCIADOS A LA CALIDAD DE LA FORMACIÓN PREDOCTORAL");
		publicationsTitle3A6.addBreak();

		XWPFRun predoctoral = paragraph.createRun();
		for (String s : map.get("OTROS PREDOCTORAL")) {
			predoctoral.setFontSize(9);
			predoctoral.setText(s);
			predoctoral.addBreak();
		}
		predoctoral.addBreak();

		XWPFRun publicationsTitle3A7 = paragraph.createRun();
		publicationsTitle3A7.setBold(true);
		publicationsTitle3A7.setFontSize(10);
		publicationsTitle3A7.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A7
				.setText("3.A.7. OTROS MÉRITOS ASOCIADOS A LA CALIDAD DE LA FORMACIÓN POSTDOCTORAL.");
		publicationsTitle3A7.addBreak();

		XWPFRun postdoctoral = paragraph.createRun();
		for (String s : map.get("OTROS POSTDOCTORAL")) {
			postdoctoral.setFontSize(9);
			postdoctoral.setText(s);
			postdoctoral.addBreak();
		}
		postdoctoral.addBreak();

		XWPFRun publicationsTitle3B = paragraph.createRun();
		publicationsTitle3B.setBold(true);
		publicationsTitle3B.setFontSize(12);
		publicationsTitle3B
				.setText("3.B OTROS MÉRITOS ASOCIADOS A LA FORMACIÓN ACADÉMICA.");
		publicationsTitle3B.addBreak();
		publicationsTitle3B.addBreak();

		XWPFRun academic = paragraph.createRun();
		for (String s : map.get("OTROS ACADÉMICA")) {
			academic.setFontSize(9);
			academic.setText(s);
			academic.addBreak();
		}
		academic.addBreak();

		XWPFRun publicationsTitle4 = paragraph.createRun();
		publicationsTitle4.setBold(true);
		publicationsTitle4.setFontSize(16);
		publicationsTitle4
				.setText("4. EXPERIENCIA EN GESTIÓN Y ADMINISTRACIÓN EDUCATIVA, CIENTÍFICA, TECNOLÓGICA Y OTROS MÉRITOS.");
		publicationsTitle4.addBreak();
		publicationsTitle4.addBreak();

		XWPFRun publicationsTitle4A = paragraph.createRun();
		publicationsTitle4A.setBold(true);
		publicationsTitle4A.setFontSize(12);
		publicationsTitle4A
				.setText("4.A. DESEMPEÑO DE CARGOS UNIPERSONALES DE RESPONSABILIDAD EN GESTIÓN UNIVERSITARIA RECOGIDOS EN LOS ESTATUTOS DE LAS UNIVERSIDADES, O QUE HAYAN SIDO ASIMILADOS, U ORGANISMOS PÚBLICOS DE INVESTIGACIÓN DURANTE AL MENOS UN AÑO.");
		publicationsTitle4A.addBreak();
		publicationsTitle4A.addBreak();

		XWPFRun managementExperience = paragraph.createRun();
		for (String s : map.get("EXPERIENCIA GESTIÓN")) {
			managementExperience.setFontSize(9);
			managementExperience.setText(s);
			managementExperience.addBreak();
		}
		managementExperience.addBreak();

		XWPFRun publicationsTitle4B = paragraph.createRun();
		publicationsTitle4B.setBold(true);
		publicationsTitle4B.setFontSize(12);
		publicationsTitle4B
				.setText("4.B. DESEMPEÑO DE PUESTOS EN EL ENTORNO EDUCATIVO, CIENTÍFICO O TECNOLÓGICO DENTRO DE LA ADMINISTRACIÓN GENERAL DEL ESTADO O DE LAS COMUNIDADES AUTÓNOMAS DURANTE AL MENOS UN AÑO.");
		publicationsTitle4B.addBreak();
		publicationsTitle4B.addBreak();

		XWPFRun positionAdministration = paragraph.createRun();
		for (String s : map.get("PUESTO ADMINISTRACIÓN")) {
			positionAdministration.setFontSize(9);
			positionAdministration.setText(s);
			positionAdministration.addBreak();
		}
		positionAdministration.addBreak();

		XWPFRun publicationsTitle4C = paragraph.createRun();
		publicationsTitle4C.setBold(true);
		publicationsTitle4C.setFontSize(12);
		publicationsTitle4C
				.setText("4.C. OTROS MÉRITOS RELACIONADOS CON LA EXPERIENCIA EN GESTIÓN Y ADMINISTRACIÓN.");
		publicationsTitle4C.addBreak();
		publicationsTitle4C.addBreak();

		XWPFRun otherExperience = paragraph.createRun();
		for (String s : map.get("OTROS EXPERIENCIA")) {
			otherExperience.setFontSize(9);
			otherExperience.setText(s);
			otherExperience.addBreak();
		}
		otherExperience.addBreak();

		try {
			FileOutputStream fos = new FileOutputStream(new File(
					saveFile.getText() + ".docx"));
			document.write(fos);
			fos.close();
			dispose();
		} catch (IOException e) {
			System.out.println("HORROR");
		}
	}

	private String formatString(String cadena) {
		if (!cadena.equals("")) {
			String entry = "";
			entry += cadena.charAt(0);
			for (int i = 1; i < cadena.length(); i++) {
				entry += Character.toLowerCase(cadena.charAt(i));
			}
			return entry;
		}
		return cadena;
	}

	private int[] do1A1(PdfReader reader, int pageIndex, int numPages)
			throws IOException {
		String cadena = "";
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (!split[i]
						.contains("1.A.2. PUBLICACIONES CIENTÍFICAS NO INDEXADAS")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("Artículo")
									&& !split[i + x].contains("D.O.I.:")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
					}
					if (split[i].contains("NOMBRE DE LA REVISTA")) {
						cadena += " en ";
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("VOLUMEN:")
									&& !split[i + x].contains("esde")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PUBLICACIÓN").add(formatString(cadena));
						cadena = "";
					}

				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1A2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("1.A.3. LIBROS Y CAPÍTULOS DE LIBROS")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("D.O.I.:")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
					}
					if (split[i].contains("NOMBRE DE LA REVISTA")) {
						cadena += " en ";
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("VOLUMEN:")
									&& !split[i + x].contains("esde")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PUBLICACIÓN NO INDEXADA").add(
								formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1A3(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("1.A.5. CONGRESOS")) {
					if (split[i].contains("TÍTULO LIBRO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("TÍTULO CAPÍTULO:")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("LIBRO").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1A5(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("1.A.6. CONFERENCIAS Y SEMINARIOS")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("AUTORES:")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("CONGRESO").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1A6(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("1.A.7. OTROS MÉRITOS RELACIONADOS")) {
					if (split[i].contains("TÍTULO")) {
						if (!split[i - 1].contains("AUTORES:")) {
							cadena += split[i - 1] + " ";
							for (int x = 1; i + x < split.length; x++) {
								if (!split[i + x].contains("ENTIDAD:")) {
									cadena += split[i + x] + " ";
								} else {
									break;
								}
							}
						}
					}
					if (split[i].contains("DENOMINACIÓN")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("TIPO PARTICIPACIÓN")) {
								if (split[i + x + 1]
										.contains("TIPO PARTICIPACIÓN")) {
									cadena += ". Tipo de participación: ";
								}
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("CONFERENCIA").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1A7(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("ACTIVIDAD INVESTIGADORA")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("1.B. CALIDAD Y NÚMERO DE PROYECTOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS CALIDAD").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i]
						.contains("1.B. CALIDAD Y NÚMERO DE PROYECTOS")) {
					if (!cadena.equals("")) {
						map.get("OTROS CALIDAD").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1B1(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("1.B.2. OTROS MÉRITOS RELACIONADOS")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x]
									.contains("Proyecto de investigación")
									&& !split[i + x]
											.contains("TIPO DE PARTICIPACIÓN:")
									&& !split[i + x]
											.contains("Contrato de investigación")
									&& !split[i + x].equals("")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PROYECTO").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1B2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("DE INVESTIGACIÓN") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("1.C. CALIDAD DE LA TRANSFERENCIA")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS NÚMERO").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i]
						.contains("1.C. CALIDAD DE LA TRANSFERENCIA")) {
					if (!cadena.equals("")) {
						map.get("OTROS NÚMERO").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1C2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("1.C.2. TRANSFERENCIA DE CONOCIMIENTO")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("1.C.3. OTROS MÉRITOS RELACIONADOS")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("TRANSFERENCIA").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i]
						.contains("1.C.3. OTROS MÉRITOS RELACIONADOS")) {
					if (!cadena.equals("")) {
						map.get("TRANSFERENCIA").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1C3(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("RESULTADOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("1.D. MOVILIDAD DEL PROFESORADO")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS TRANSFERENCIA").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("1.D. MOVILIDAD DEL PROFESORADO")) {
					if (!cadena.equals("")) {
						map.get("OTROS TRANSFERENCIA")
								.add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1D1(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("1.D.2. OTROS MÉRITOS RELACIONADOS")) {
					if (split[i].contains("INSTITUCIÓN:")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("LOCALIDAD:")) {
								if (!split[i + x].contains("CENTRO:")) {
									cadena += split[i + x] + " ";
								}
							} else {
								break;
							}
						}
						map.get("ESTANCIA").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do1D2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("ACTIVIDAD INVESTIGADORA")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("2. ACTIVIDAD DOCENTE O PROFESIONAL")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS INVESTIGACIÓN").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i]
						.contains("2. ACTIVIDAD DOCENTE O PROFESIONAL")) {
					if (!cadena.equals("")) {
						map.get("OTROS INVESTIGACIÓN")
								.add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2A1(PdfReader reader, int pageIndex, int numPages,
			int nexLine) throws IOException {
		String cadena = "";
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (!split[i].contains("2.A.2. DIRECCIÓN DE TESIS DOCTORALES")) {
					if (split[i].contains("DENOMINACIÓN")) {
						String[] subSplit = split[i].split(": ");
						if (subSplit.length > 1) {
							cadena += subSplit[1];
						}
					}
					if (split[i].contains("DEPARTAMENTO")) {
						cadena += " en el departamento de ";
						String[] subSplit = split[i].split(": ");
						if (subSplit.length > 1) {
							cadena += subSplit[1];
						}
					}
					if (split[i].contains("INSTITUCIÓN")) {
						cadena += " en la ";
						String[] subSplit = split[i].split(": ");
						if (subSplit.length > 1) {
							cadena += subSplit[1];
						}
						map.get("PUESTO").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2A2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("2.A.3. DIRECCIÓN DE PROYECTOS")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("DOCTORANDO")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("TESIS DOCTORALES").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2A3(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("ETC.") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("2.A.4. OTROS MÉRITOS RELACIONADOS")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("DIRECCIÓN PROYECTO").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i]
						.contains("2.A.4. OTROS MÉRITOS RELACIONADOS")) {
					if (!cadena.equals("")) {
						map.get("DIRECCIÓN PROYECTO").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2A4(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("2.A.4. OTROS MÉRITOS RELACIONADOS")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("2.B. CALIDAD DE LA ACTIVIDAD")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS DOCENTE").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("2.B. CALIDAD DE LA ACTIVIDAD")) {
					if (!cadena.equals("")) {
						map.get("OTROS DOCENTE").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2B1(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("2.B.1. EVALUACIONES") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("2.B.2. MATERIAL DOCENTE ORIGINAL")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("EVALUACIONES POSITIVAS").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i]
						.contains("2.B.2. MATERIAL DOCENTE ORIGINAL")) {
					if (!cadena.equals("")) {
						map.get("EVALUACIONES POSITIVAS").add(
								formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2B2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i]
						.contains("2.B.3. PROYECTOS DE INNOVACIÓN DOCENTE")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("Editor")
									&& !split[i + x].equals("")
									&& !split[i + x].contains("CLAVE:")
									&& !split[i + x].contains("Apuntes")
									&& !split[i + x].contains("Libro completo")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("MATERIAL ORIGINAL").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2B3(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("2.B.4. OTROS MÉRITOS RELACIONADOS")) {
					if (split[i].contains("TÍTULO DEL PROYECTO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("ENTIDAD FINANCIADORA")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PROYECTO INNOVACIÓN")
								.add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2B4(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("2.B.4. OTROS MÉRITOS RELACIONADOS")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("2.C CALIDAD DE LA FORMACIÓN DOCENTE")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS CALIDAD ACTIVIDAD").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i]
						.contains("2.C CALIDAD DE LA FORMACIÓN DOCENTE")) {
					if (!cadena.equals("")) {
						map.get("OTROS CALIDAD ACTIVIDAD").add(
								formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2C1(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("2.C.2. PARTICIPACIÓN, COMO ASISTENTE")) {
					if (split[i].contains("TÍTULO")) {
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("CONGRESO:")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PARTICIPACIÓN PONENTE").add(
								formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2C2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("2.C.3. ESTANCIAS")) {
					if (split[i].contains("TÍTULO")) {
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("OBJETIVOS DEL CURSO")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PARTICIPACIÓN ASISTENTE").add(
								formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2C3(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("2.C.4.OTROS MÉRITOS RELACIONADOS")) {
					if (split[i].contains("INSTITUCIÓN:")) {
						String[] text = split[i].split(": ");
						if (text.length > 0) {
							cadena += text[text.length - 1];
						}
						if (!split[i - 1].contains("CENTRO")) {
							cadena += split[i - 1] + " ";
						}
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("LOCALIDAD")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("ESTANCIA DOCENTE").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2C4(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("2.C.4.OTROS MÉRITOS RELACIONADOS")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("2.D CALIDAD Y DEDICACIÓN")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS FORMACIÓN").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("2.D CALIDAD Y DEDICACIÓN")) {
					if (!cadena.equals("")) {
						map.get("OTROS FORMACIÓN").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2D1(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("2.D.2. EVALUACIONES POSITIVAS")) {
					if (split[i].contains("CATEGORÍA PROFESIONAL")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x]
									.contains("En las áreas clínicas de Ciencias de la Salud")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PUESTOS DEDICACIÓN").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2D2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("2.D.2. EVALUACIONES POSITIVAS")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("2.E. OTROS MÉRITOS RELACIONADOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("EVALUACIONES ACTIVIDAD").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("2.E. OTROS MÉRITOS RELACIONADOS")) {
					if (!cadena.equals("")) {
						map.get("EVALUACIONES ACTIVIDAD").add(
								formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do2E(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("2.E. OTROS MÉRITOS RELACIONADOS")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("3. FORMACIÓN ACADÉMICA")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS MÉRITOS").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("3. FORMACIÓN ACADÉMICA")) {
					if (!cadena.equals("")) {
						map.get("OTROS MÉRITOS").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do3A1(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("3.A.2. TESIS DOCTORAL")) {
					if (split[i].contains("TITULACIÓN:")) {
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("UNIVERSIDAD")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("TITULACIÓN UNIVERSITARIA").add(
								formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do3A2(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("3.A.3. OTROS TÍTULOS")) {
					if (split[i].contains("TÍTULO:")) {
						if (!split[i - 1].contains("UNIVERSIDAD:")) {
							cadena += split[i - 1] + " ";
						}
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("FECHA")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("TESIS DOCTORAL").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do3A3(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("3.A.4. BECAS Y AYUDAS")) {
					if (split[i].contains("DENOMINACIÓN DEL TÍTULO:")) {
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("INSTITUCIÓN")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("OTROS TÍTULOS").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do3A4(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("3.A.5. PREMIOS")) {
					if (split[i].contains("FINALIDAD:")) {
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("CANTIDAD FINANCIADA")) {
								if (!split[i + x]
										.contains("ENTIDAD FINANCIADORA")) {
									cadena += split[i + x] + " ";
								}
							} else {
								break;
							}
						}
						map.get("BECAS").add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do3A5(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("3.A.5. PREMIOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("3.A.6. OTROS MÉRITOS ASOCIADOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("PREMIOS").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("3.A.6. OTROS MÉRITOS ASOCIADOS")) {
					if (!cadena.equals("")) {
						map.get("PREMIOS").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do3A6(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("3.A.6. OTROS MÉRITOS ASOCIADOS")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x]
									.contains("3.A.7. OTROS MÉRITOS ASOCIADOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS PREDOCTORAL").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("3.A.7. OTROS MÉRITOS ASOCIADOS")) {
					if (!cadena.equals("")) {
						map.get("OTROS PREDOCTORAL").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do3A7(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("3.A.7. OTROS MÉRITOS ASOCIADOS")
						|| reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("3.B OTROS MÉRITOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS POSTDOCTORAL").add(
										formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("3.B OTROS MÉRITOS")) {
					if (!cadena.equals("")) {
						map.get("OTROS POSTDOCTORAL").add(formatString(cadena));
					}
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do4A(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("4.B. DESEMPEÑO DE PUESTOS")) {
					if (split[i].contains("DENOMINACIÓN:")) {
						String[] text = split[i].split(": ");
						if (text.length > 0) {
							cadena += text[text.length - 1];
						}
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("ACTIVIDAD:")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("EXPERIENCIA GESTIÓN")
								.add(formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do4B(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (!split[i].contains("4.C. OTROS MÉRITOS")) {
					if (split[i].contains("DENOMINACIÓN:")) {
						String[] text = split[i].split(": ");
						if (text.length > 0) {
							cadena += text[text.length - 1];
						}
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("ACTIVIDAD:")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PUESTO ADMINISTRACIÓN").add(
								formatString(cadena));
						cadena = "";
					}
				} else {
					int[] index = new int[2];
					index[0] = j;
					index[1] = i;
					return index;
				}
			}
		}
		return null;
	}

	private int[] do4C(PdfReader reader, int pageIndex, int numPages,
			int lineIndex) throws IOException {
		String cadena = "";
		boolean firstLoop = true;
		boolean reading = false;
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (firstLoop) {
					i = lineIndex;
					firstLoop = false;
				}
				if (split[i].contains("ADMINISTRACIÓN.") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							cadena += split[i + x] + " ";
						}
					}
					break;
				}

			}
		}
		map.get("OTROS EXPERIENCIA").add(formatString(cadena));
		int[] index = new int[2];
		index[0] = 0;
		index[1] = 0;
		return index;
	}

	public void append(String word) {
		if (builder == null) {
			this.builder = new StringBuilder();
		}
		builder.append(word);
	}

	public String getText() {
		return builder.toString();
	}
}
