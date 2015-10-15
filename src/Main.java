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
					SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeSkin");
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
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
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
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
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
				if (textFile.getText().equals("") || saveFile.getText().equals("")) {
					Message.showError("Los dos campos 'archivo' y 'destino' no pueden estar vacíos.", btnGenerarDoc);
				} else {
					try {
						map = new HashMap<String, List<String>>();
						map.put("PUBLICACIÓN", new ArrayList<String>());
						map.put("PUBLICACIÓN NO INDEXADA", new ArrayList<String>());
						map.put("LIBRO", new ArrayList<String>());
						map.put("CONGRESO", new ArrayList<String>());
						map.put("CONFERENCIA", new ArrayList<String>());
						map.put("OTROS CALIDAD", new ArrayList<String>());
						map.put("OTROS DOCENTE", new ArrayList<String>());
						map.put("OTROS INVESTIGACIÓN", new ArrayList<String>());
						map.put("PROYECTO", new ArrayList<String>());
						map.put("PROYECTO INNOVACIÓN", new ArrayList<String>());
						map.put("PATENTES Y PRODUCTOS", new ArrayList<String>());
						map.put("OTROS CALIDAD ACTIVIDAD", new ArrayList<String>());
						map.put("DIRECCIÓN PROYECTO", new ArrayList<String>());
						map.put("MATERIAL ORIGINAL", new ArrayList<String>());
						map.put("ESTANCIA", new ArrayList<String>());
						map.put("PUESTO", new ArrayList<String>());
						map.put("MATERIAL DOCENTE", new ArrayList<String>());
						map.put("CURSO", new ArrayList<String>());
						map.put("OTROS NÚMERO", new ArrayList<String>());
						map.put("TRANSFERENCIA", new ArrayList<String>());
						map.put("EVALUACIONES POSITIVAS", new ArrayList<String>());
						map.put("OTROS TRANSFERENCIA", new ArrayList<String>());
						map.put("TESIS DOCTORALES", new ArrayList<String>());
						map.put("PARTICIPACIÓN PONENTE", new ArrayList<String>());
						map.put("PARTICIPACIÓN ASISTENTE", new ArrayList<String>());
						map.put("ESTANCIA DOCENTE", new ArrayList<String>());
						map.put("OTROS FORMACIÓN", new ArrayList<String>());
						map.put("PUESTOS DEDICACIÓN", new ArrayList<String>());
						map.put("EVALUACIONES ACTIVIDAD", new ArrayList<String>());
						map.put("OTROS MÉRITOS", new ArrayList<String>());
						map.put("TITULACIÓN UNIVERSITARIA", new ArrayList<String>());
						map.put("TESIS DOCTORAL", new ArrayList<String>());
						map.put("OTROS TÍTULOS", new ArrayList<String>());
						map.put("BECAS", new ArrayList<String>());
						map.put("PREMIOS", new ArrayList<String>());
						map.put("OTROS PREDOCTORAL", new ArrayList<String>());
						map.put("OTROS POSTDOCTORAL", new ArrayList<String>());
						map.put("OTROS ACADÉMICA", new ArrayList<String>());
						map.put("OTROS EXPERIENCIA", new ArrayList<String>());
						map.put("PUESTO ADMINISTRACIÓN", new ArrayList<String>());
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

		XWPFParagraph publicationsParagraph = document.createParagraph();
		XWPFRun publicationsTitle = publicationsParagraph.createRun();
		publicationsTitle.setBold(true);
		publicationsTitle.setFontSize(18);
		publicationsTitle.setText("RELACIÓN DE DOCUMENTOS APORTADOS");

		XWPFParagraph publicationsTitle1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1 = publicationsTitle1Paragraph.createRun();
		publicationsTitle1.setBold(true);
		publicationsTitle1.setFontSize(16);
		publicationsTitle1.setText("1. ACTIVIDAD INVESTIGADORA.");

		XWPFParagraph publicationsTitle1AParagraph = document.createParagraph();
		XWPFRun publicationsTitle1A = publicationsTitle1AParagraph.createRun();
		publicationsTitle1A.setBold(true);
		publicationsTitle1A.setFontSize(12);
		publicationsTitle1A.setText("1.A. CALIDAD Y DIFUSIÓN DE RESULTADOS DE LA ACTIVIDAD INVESTIGADORA");

		XWPFParagraph publicationsTitle1A1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1A1 = publicationsTitle1A1Paragraph.createRun();
		publicationsTitle1A1.setBold(true);
		publicationsTitle1A1.setFontSize(10);
		publicationsTitle1A1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A1
				.setText("1.A.1. PUBLICACIONES CIENTÍFICAS INDEXADAS DE ACUERDO CON UN ÍNDICE DE CALIDAD RELATIVO");

		for (String s : map.get("PUBLICACIÓN")) {
			XWPFParagraph publicationParagraph = document.createParagraph();
			XWPFRun publication = publicationParagraph.createRun();
			publication.setFontSize(9);
			publication.setText(s);

		}

		XWPFParagraph publicationsTitle1A2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1A2 = publicationsTitle1A2Paragraph.createRun();
		publicationsTitle1A2.setBold(true);
		publicationsTitle1A2.setFontSize(10);
		publicationsTitle1A2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A2
				.setText("1.A.2. PUBLICACIONES CIENTÍFICAS NO INDEXADAS DE ACUERDO CON UN ÍNDICE DE CALIDAD RELATIVO");
		publicationsTitle1A2.addBreak();

		for (String s : map.get("PUBLICACIÓN NO INDEXADA")) {
			XWPFParagraph notIndexedPublicationParagraph = document.createParagraph();
			XWPFRun notIndexedPublication = notIndexedPublicationParagraph.createRun();
			notIndexedPublication.setFontSize(9);
			notIndexedPublication.setText(s);

		}

		XWPFParagraph publicationsTitle1A3Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1A3 = publicationsTitle1A3Paragraph.createRun();
		publicationsTitle1A3.setBold(true);
		publicationsTitle1A3.setFontSize(10);
		publicationsTitle1A3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A3.setText("1.A.3. LIBROS Y CAPÍTULOS DE LIBROS");

		for (String s : map.get("LIBRO")) {
			XWPFParagraph libroParagraph = document.createParagraph();
			XWPFRun libro = libroParagraph.createRun();
			libro.setFontSize(9);
			libro.setText(s);

		}

		XWPFParagraph publicationsTitle1A4Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1A4 = publicationsTitle1A4Paragraph.createRun();
		publicationsTitle1A4.setBold(true);
		publicationsTitle1A4.setFontSize(10);
		publicationsTitle1A4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A4.setText("1.A.4. CREACIONES ARTÍSTICAS Y PROFESIONALES");

		XWPFParagraph avisoParagraph = document.createParagraph();
		XWPFRun aviso = avisoParagraph.createRun();
		aviso.setFontSize(9);
		aviso.setColor("FF0000");
		aviso.setBold(true);
		aviso.setText("ACUERDATE DE RELLENAR ESTO SI LO HAY");

		XWPFParagraph publicationsTitle1A5Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1A5 = publicationsTitle1A5Paragraph.createRun();
		publicationsTitle1A5.setBold(true);
		publicationsTitle1A5.setFontSize(10);
		publicationsTitle1A5.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A5.setText("1.A.5. CONGRESOS");

		for (String s : map.get("CONGRESO")) {
			XWPFParagraph congressParagraph = document.createParagraph();
			XWPFRun congress = congressParagraph.createRun();
			congress.setFontSize(9);
			congress.setText(s);
		}
		XWPFParagraph congressParagraph = document.createParagraph();
		XWPFRun congress = congressParagraph.createRun();
		congress.addBreak();

		XWPFParagraph publicationsTitle1A6Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1A6 = publicationsTitle1A6Paragraph.createRun();
		publicationsTitle1A6.setBold(true);
		publicationsTitle1A6.setFontSize(10);
		publicationsTitle1A6.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A6.setText("1.A.6. CONFERENCIAS Y SEMINARIOS");

		for (String s : map.get("CONFERENCIA")) {
			XWPFParagraph conferenceParagraph = document.createParagraph();
			XWPFRun conference = conferenceParagraph.createRun();
			conference.setFontSize(9);
			conference.setText(s);
		}

		XWPFParagraph publicationsTitle1A7Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1A7 = publicationsTitle1A7Paragraph.createRun();
		publicationsTitle1A7.setBold(true);
		publicationsTitle1A7.setFontSize(10);
		publicationsTitle1A7.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1A7.setText(
				"1.A.7. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD Y DIFUSIÓN DE RESULTADOS DE LA ACTIVIDAD INVESTIGADORA");

		for (String s : map.get("OTROS CALIDAD")) {
			XWPFParagraph otherQualityParagraph = document.createParagraph();
			XWPFRun otherQuality = otherQualityParagraph.createRun();
			otherQuality.setFontSize(9);
			otherQuality.setText(s);

		}

		XWPFParagraph publicationsTitle1BParagraph = document.createParagraph();
		XWPFRun publicationsTitle1B = publicationsTitle1BParagraph.createRun();
		publicationsTitle1B.setBold(true);
		publicationsTitle1B.setFontSize(12);
		publicationsTitle1B.setText("1.B. CALIDAD Y NÚMERO DE PROYECTOS Y CONTRATOS DE INVESTIGACIÓN");

		XWPFParagraph publicationsTitle1B1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1B1 = publicationsTitle1B1Paragraph.createRun();
		publicationsTitle1B1.setBold(true);
		publicationsTitle1B1.setFontSize(10);
		publicationsTitle1B1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1B1
				.setText("1.B.1. PARTICIPACIÓN EN PROYECTOS DE INVESTIGACIÓN Y/O EN CONTRATOS DE INVESTIGACIÓN");

		for (String s : map.get("PROYECTO")) {

			XWPFParagraph projectParagraph = document.createParagraph();
			XWPFRun project = projectParagraph.createRun();
			project.setFontSize(9);
			project.setText(s);

		}

		XWPFParagraph publicationsTitle1B2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1B2 = publicationsTitle1B2Paragraph.createRun();
		publicationsTitle1B2.setBold(true);
		publicationsTitle1B2.setFontSize(10);
		publicationsTitle1B2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1B2.setText(
				"1.B.2. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD Y NÚMERO DE PROYECTOS Y CONTRATOS DE INVESTIGACIÓN");

		for (String s : map.get("OTROS NÚMERO")) {
			XWPFParagraph otherNumberParagraph = document.createParagraph();
			XWPFRun otherNumber = otherNumberParagraph.createRun();
			otherNumber.setFontSize(9);
			otherNumber.setText(s);

		}

		XWPFParagraph publicationsTitle1CParagraph = document.createParagraph();
		XWPFRun publicationsTitle1C = publicationsTitle1CParagraph.createRun();
		publicationsTitle1C.setBold(true);
		publicationsTitle1C.setFontSize(12);
		publicationsTitle1C.setText("1.C. CALIDAD DE LA TRANSFERENCIA DE LOS RESULTADOS");

		XWPFParagraph publicationsTitle1C1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1C1 = publicationsTitle1C1Paragraph.createRun();
		publicationsTitle1C1.setBold(true);
		publicationsTitle1C1.setFontSize(10);
		publicationsTitle1C1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1C1.setText("1.C.1. PATENTES Y PRODUCTOS CON REGISTRO DE PROPIEDAD INTELECTUAL");

		XWPFParagraph avisoPatentesParagraph = document.createParagraph();
		XWPFRun avisoPatentes = avisoPatentesParagraph.createRun();
		avisoPatentes.setFontSize(9);
		avisoPatentes.setColor("FF0000");
		avisoPatentes.setBold(true);
		avisoPatentes.setText("ACUERDATE DE RELLENAR ESTO SI LO HAY");

		XWPFParagraph publicationsTitle1C2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1C2 = publicationsTitle1C2Paragraph.createRun();
		publicationsTitle1C2.setBold(true);
		publicationsTitle1C2.setFontSize(10);
		publicationsTitle1C2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1C2.setText("1.C.2. TRANSFERENCIA DE CONOCIMIENTO AL SECTOR PRODUCTIVO.");

		for (String s : map.get("TRANSFERENCIA")) {
			XWPFParagraph transferenceParagraph = document.createParagraph();
			XWPFRun transference = transferenceParagraph.createRun();
			transference.setFontSize(9);
			transference.setText(s);

		}

		XWPFParagraph publicationsTitle1C3Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1C3 = publicationsTitle1C3Paragraph.createRun();
		publicationsTitle1C3.setBold(true);
		publicationsTitle1C3.setFontSize(10);
		publicationsTitle1C3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1C3
				.setText("1.C.3. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD DE LA TRANSFERENCIA DE LOS RESULTADOS");

		for (String s : map.get("OTROS TRANSFERENCIA")) {
			XWPFParagraph otherTransferenceParagraph = document.createParagraph();
			XWPFRun otherTransference = otherTransferenceParagraph.createRun();
			otherTransference.setFontSize(9);
			otherTransference.setText(s);

		}

		XWPFParagraph publicationsTitle1DParagraph = document.createParagraph();
		XWPFRun publicationsTitle1D = publicationsTitle1DParagraph.createRun();
		publicationsTitle1D.setBold(true);
		publicationsTitle1D.setFontSize(12);
		publicationsTitle1D.setText("1.D. MOVILIDAD DEL PROFESORADO");

		XWPFParagraph publicationsTitle1D1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1D1 = publicationsTitle1D1Paragraph.createRun();
		publicationsTitle1D1.setBold(true);
		publicationsTitle1D1.setFontSize(10);
		publicationsTitle1D1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1D1.setText("1.D.1. ESTANCIAS EN CENTROS DE INVESTIGACIÓN");

		for (String s : map.get("ESTANCIA")) {
			XWPFParagraph stayParagraph = document.createParagraph();
			XWPFRun stay = stayParagraph.createRun();
			stay.setFontSize(9);
			stay.setText(s);

		}

		XWPFParagraph publicationsTitle1D2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle1D2 = publicationsTitle1D2Paragraph.createRun();
		publicationsTitle1D2.setBold(true);
		publicationsTitle1D2.setFontSize(10);
		publicationsTitle1D2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle1D2.setText("1.D.2. OTROS MÉRITOS RELACIONADOS CON LA MOVILIDAD DEL PROFESORADO");

		XWPFParagraph publicationsTitle1EParagraph = document.createParagraph();
		XWPFRun publicationsTitle1E = publicationsTitle1EParagraph.createRun();
		publicationsTitle1E.setBold(true);
		publicationsTitle1E.setFontSize(12);
		publicationsTitle1E.setText("1.E. OTROS MÉRITOS RELACIONADOS CON LA ACTIVIDAD INVESTIGADORA");

		for (String s : map.get("OTROS INVESTIGACIÓN")) {
			XWPFParagraph othersInvestigationParagraph = document.createParagraph();
			XWPFRun othersInvestigation = othersInvestigationParagraph.createRun();
			othersInvestigation.setFontSize(9);
			othersInvestigation.setText(s);

		}

		XWPFParagraph publicationsTitle2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2 = publicationsTitle2Paragraph.createRun();
		publicationsTitle2.setBold(true);
		publicationsTitle2.setFontSize(16);
		publicationsTitle2.setText("2. ACTIVIDAD DOCENTE O PROFESIONAL.");

		XWPFParagraph publicationsTitle2AParagraph = document.createParagraph();
		XWPFRun publicationsTitle2A = publicationsTitle2AParagraph.createRun();
		publicationsTitle2A.setBold(true);
		publicationsTitle2A.setFontSize(12);
		publicationsTitle2A.setText("2.A. DEDICACIÓN DOCENTE");

		XWPFParagraph publicationsTitle2A1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2A1 = publicationsTitle2A1Paragraph.createRun();
		publicationsTitle2A1.setBold(true);
		publicationsTitle2A1.setFontSize(10);
		publicationsTitle2A1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A1.setText("2.A.1. PUESTOS DOCENTES OCUPADOS");

		for (String s : map.get("PUESTO")) {
			XWPFParagraph positionParagraph = document.createParagraph();
			XWPFRun position = positionParagraph.createRun();
			position.setFontSize(9);
			position.setText(s);

		}

		XWPFParagraph publicationsTitle2A2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2A2 = publicationsTitle2A2Paragraph.createRun();
		publicationsTitle2A2.setBold(true);
		publicationsTitle2A2.setFontSize(10);
		publicationsTitle2A2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A2.setText("2.A.2. DIRECCIÓN DE TESIS DOCTORALES");

		for (String s : map.get("TESIS DOCTORALES")) {
			XWPFParagraph tesisParagraph = document.createParagraph();
			XWPFRun tesis = tesisParagraph.createRun();
			tesis.setFontSize(9);
			tesis.setText(s);

		}

		XWPFParagraph publicationsTitle2A3Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2A3 = publicationsTitle2A3Paragraph.createRun();
		publicationsTitle2A3.setBold(true);
		publicationsTitle2A3.setFontSize(10);
		publicationsTitle2A3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A3
				.setText("2.A.3. DIRECCIÓN DE PROYECTOS FIN DE CARRERA, TESINAS, TRABAJOS FIN DE MÁSTER, ETC.");

		for (String s : map.get("DIRECCIÓN PROYECTO")) {
			XWPFParagraph projectDirectionParagraph = document.createParagraph();
			XWPFRun projectDirection = projectDirectionParagraph.createRun();
			projectDirection.setFontSize(9);
			projectDirection.setText(s);

		}

		XWPFParagraph publicationsTitle2A4Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2A4 = publicationsTitle2A4Paragraph.createRun();
		publicationsTitle2A4.setBold(true);
		publicationsTitle2A4.setFontSize(10);
		publicationsTitle2A4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2A4.setText("2.A.4. OTROS MÉRITOS RELACIONADOS CON LA ACTIVIDAD DOCENTE");

		for (String s : map.get("OTROS DOCENTE")) {
			XWPFParagraph othersDocentParagraph = document.createParagraph();
			XWPFRun othersDocent = othersDocentParagraph.createRun();
			othersDocent.setFontSize(9);
			othersDocent.setText(s);

		}

		XWPFParagraph publicationsTitle2BParagraph = document.createParagraph();
		XWPFRun publicationsTitle2B = publicationsTitle2BParagraph.createRun();
		publicationsTitle2B.setBold(true);
		publicationsTitle2B.setFontSize(12);
		publicationsTitle2B.setText("2.B. CALIDAD DE LA ACTIVIDAD DOCENTE");

		XWPFParagraph publicationsTitle2B1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2B1 = publicationsTitle2B1Paragraph.createRun();
		publicationsTitle2B1.setBold(true);
		publicationsTitle2B1.setFontSize(10);
		publicationsTitle2B1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B1.setText("2.B.1. EVALUACIONES POSITIVAS DE SU ACTIVIDAD");

		for (String s : map.get("EVALUACIONES POSITIVAS")) {
			XWPFParagraph positiveEvaluationsParagraph = document.createParagraph();
			XWPFRun positiveEvaluations = positiveEvaluationsParagraph.createRun();
			positiveEvaluations.setFontSize(9);
			positiveEvaluations.setText(s);

		}

		XWPFParagraph publicationsTitle2B2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2B2 = publicationsTitle2B2Paragraph.createRun();
		publicationsTitle2B2.setBold(true);
		publicationsTitle2B2.setFontSize(10);
		publicationsTitle2B2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B2.setText("2.B.2. MATERIAL DOCENTE ORIGINAL Y PUBLICACIONES DOCENTES");

		for (String s : map.get("MATERIAL ORIGINAL")) {
			XWPFParagraph originalMaterialParagraph = document.createParagraph();
			XWPFRun originalMaterial = originalMaterialParagraph.createRun();
			originalMaterial.setFontSize(9);
			originalMaterial.setText(s);

		}

		XWPFParagraph publicationsTitle2B3Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2B3 = publicationsTitle2B3Paragraph.createRun();
		publicationsTitle2B3.setBold(true);
		publicationsTitle2B3.setFontSize(10);
		publicationsTitle2B3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B3.setText("2.B.3. PROYECTOS DE INNOVACIÓN DOCENTE");

		for (String s : map.get("PROYECTO INNOVACIÓN")) {
			XWPFParagraph innovationProjectParagraph = document.createParagraph();
			XWPFRun innovationProject = innovationProjectParagraph.createRun();
			innovationProject.setFontSize(9);
			innovationProject.setText(s);

		}

		XWPFParagraph publicationsTitle2B4Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2B4 = publicationsTitle2B4Paragraph.createRun();
		publicationsTitle2B4.setBold(true);
		publicationsTitle2B4.setFontSize(10);
		publicationsTitle2B4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2B4.setText("2.B.4. OTROS MÉRITOS RELACIONADOS CON LA CALIDAD DE LA ACTIVIDAD DOCENTE");

		for (String s : map.get("OTROS CALIDAD ACTIVIDAD")) {
			XWPFParagraph othersQualityActivityParagraph = document.createParagraph();
			XWPFRun othersQualityActivity = othersQualityActivityParagraph.createRun();
			othersQualityActivity.setFontSize(9);
			othersQualityActivity.setText(s);

		}

		XWPFParagraph publicationsTitle2CParagraph = document.createParagraph();
		XWPFRun publicationsTitle2C = publicationsTitle2CParagraph.createRun();
		publicationsTitle2C.setBold(true);
		publicationsTitle2C.setFontSize(12);
		publicationsTitle2C.setText("2.C CALIDAD DE LA FORMACIÓN DOCENTE");

		XWPFParagraph publicationsTitle2C1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2C1 = publicationsTitle2C1Paragraph.createRun();
		publicationsTitle2C1.setBold(true);
		publicationsTitle2C1.setFontSize(10);
		publicationsTitle2C1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C1.setText(
				"2.C.1. PARTICIPACIÓN, COMO PONENTE, EN CONGRESOS ORIENTADOS A LA FORMACIÓN DOCENTE UNIVERSITARIA");

		for (String s : map.get("PARTICIPACIÓN PONENTE")) {
			XWPFParagraph ponentParticipationParagraph = document.createParagraph();
			XWPFRun ponentParticipation = ponentParticipationParagraph.createRun();
			ponentParticipation.setFontSize(9);
			ponentParticipation.setText(s);

		}

		XWPFParagraph publicationsTitle2C2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2C2 = publicationsTitle2C2Paragraph.createRun();
		publicationsTitle2C2.setBold(true);
		publicationsTitle2C2.setFontSize(10);
		publicationsTitle2C2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C2.setText(
				"2.C.2. PARTICIPACIÓN, COMO ASISTENTE, EN CONGRESOS ORIENTADOS A LA FORMACIÓN DOCENTE UNIVERSITARIA");

		for (String s : map.get("PARTICIPACIÓN ASISTENTE")) {
			XWPFParagraph assistantParticipationParagraph = document.createParagraph();
			XWPFRun assistantParticipation = assistantParticipationParagraph.createRun();
			assistantParticipation.setFontSize(9);
			assistantParticipation.setText(s);

		}

		XWPFParagraph publicationsTitle2C3Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2C3 = publicationsTitle2C3Paragraph.createRun();
		publicationsTitle2C3.setBold(true);
		publicationsTitle2C3.setFontSize(10);
		publicationsTitle2C3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C3.setText("2.C.3. ESTANCIAS EN CENTROS DOCENTES");

		for (String s : map.get("ESTANCIA DOCENTE")) {
			XWPFParagraph docentStayParagraph = document.createParagraph();
			XWPFRun docentStay = docentStayParagraph.createRun();
			docentStay.setFontSize(9);
			docentStay.setText(s);

		}

		XWPFParagraph publicationsTitle2C4Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2C4 = publicationsTitle2C4Paragraph.createRun();
		publicationsTitle2C4.setBold(true);
		publicationsTitle2C4.setFontSize(10);
		publicationsTitle2C4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2C4.setText("2.C.4.OTROS MÉRITOS RELACIONADOS CON LA CALIDAD DE LA FORMACIÓN");

		for (String s : map.get("OTROS FORMACIÓN")) {
			XWPFParagraph othersFormationParagraph = document.createParagraph();
			XWPFRun othersFormation = othersFormationParagraph.createRun();
			othersFormation.setFontSize(9);
			othersFormation.setText(s);

		}

		XWPFParagraph publicationsTitle2DParagraph = document.createParagraph();
		XWPFRun publicationsTitle2D = publicationsTitle2DParagraph.createRun();
		publicationsTitle2D.setBold(true);
		publicationsTitle2D.setFontSize(12);
		publicationsTitle2D.setText(
				"2.D CALIDAD Y DEDICACIÓN A ACTIVIDADES PROFESIONALES,EN EMPRESAS, INSTITUCIONES, ORGANISMOS PÚBLICOS DE INVESTIGACIÓN U HOSPITALES, DISTINTAS A LAS DOCENTES O INVESTIGADORAS");

		XWPFParagraph publicationsTitle2D1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2D1 = publicationsTitle2D1Paragraph.createRun();
		publicationsTitle2D1.setBold(true);
		publicationsTitle2D1.setFontSize(10);
		publicationsTitle2D1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2D1.setText("2.D.1. PUESTOS OCUPADOS Y DEDICACIÓN");

		for (String s : map.get("PUESTOS DEDICACIÓN")) {
			XWPFParagraph positionDedicationParagraph = document.createParagraph();
			XWPFRun positionDedication = positionDedicationParagraph.createRun();
			positionDedication.setFontSize(9);
			positionDedication.setText(s);

		}

		XWPFParagraph publicationsTitle2D2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle2D2 = publicationsTitle2D2Paragraph.createRun();
		publicationsTitle2D2.setBold(true);
		publicationsTitle2D2.setFontSize(10);
		publicationsTitle2D2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle2D2.setText("2.D.2. EVALUACIONES POSITIVAS DE SU ACTIVIDAD");

		for (String s : map.get("EVALUACIONES ACTIVIDAD")) {
			XWPFParagraph evaluationActivityParagraph = document.createParagraph();
			XWPFRun evaluationActivity = evaluationActivityParagraph.createRun();
			evaluationActivity.setFontSize(9);
			evaluationActivity.setText(s);

		}

		XWPFParagraph publicationsTitle2EParagraph = document.createParagraph();
		XWPFRun publicationsTitle2E = publicationsTitle2EParagraph.createRun();
		publicationsTitle2E.setBold(true);
		publicationsTitle2E.setFontSize(12);
		publicationsTitle2E.setText("2.E. OTROS MÉRITOS RELACIONADOS CON LA ACTIVIDAD PROFESIONAL");

		for (String s : map.get("OTROS MÉRITOS")) {
			XWPFParagraph othersParagraph = document.createParagraph();
			XWPFRun others = othersParagraph.createRun();
			others.setFontSize(9);
			others.setText(s);

		}

		XWPFParagraph publicationsTitle3Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3 = publicationsTitle3Paragraph.createRun();
		publicationsTitle3.setBold(true);
		publicationsTitle3.setFontSize(16);
		publicationsTitle3.setText("3. FORMACIÓN ACADÉMICA");

		XWPFParagraph publicationsTitle3AParagraph = document.createParagraph();
		XWPFRun publicationsTitle3A = publicationsTitle3AParagraph.createRun();
		publicationsTitle3A.setBold(true);
		publicationsTitle3A.setFontSize(12);
		publicationsTitle3A.setText("3.A. CALIDAD DE LA FORMACIÓN");

		XWPFParagraph publicationsTitle3A1Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3A1 = publicationsTitle3A1Paragraph.createRun();
		publicationsTitle3A1.setBold(true);
		publicationsTitle3A1.setFontSize(10);
		publicationsTitle3A1.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A1.setText("3.A.1. TITULACIÓN UNIVERSITARIA");

		for (String s : map.get("TITULACIÓN UNIVERSITARIA")) {
			XWPFParagraph titulationParagraph = document.createParagraph();
			XWPFRun titulation = titulationParagraph.createRun();
			titulation.setFontSize(9);
			titulation.setText(s);

		}

		XWPFParagraph publicationsTitle3A2Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3A2 = publicationsTitle3A2Paragraph.createRun();
		publicationsTitle3A2.setBold(true);
		publicationsTitle3A2.setFontSize(10);
		publicationsTitle3A2.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A2.setText("3.A.2. TESIS DOCTORAL");

		for (String s : map.get("TESIS DOCTORAL")) {
			XWPFParagraph tesisDoctoralParagraph = document.createParagraph();
			XWPFRun tesisDoctoral = tesisDoctoralParagraph.createRun();
			tesisDoctoral.setFontSize(9);
			tesisDoctoral.setText(s);

		}

		XWPFParagraph publicationsTitle3A3Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3A3 = publicationsTitle3A3Paragraph.createRun();
		publicationsTitle3A3.setBold(true);
		publicationsTitle3A3.setFontSize(10);
		publicationsTitle3A3.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A3.setText("3.A.3. OTROS TÍTULOS");

		for (String s : map.get("OTROS TÍTULOS")) {
			XWPFParagraph otherTitulationParagraph = document.createParagraph();
			XWPFRun otherTitulation = otherTitulationParagraph.createRun();
			otherTitulation.setFontSize(9);
			otherTitulation.setText(s);

		}

		XWPFParagraph publicationsTitle3A4Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3A4 = publicationsTitle3A4Paragraph.createRun();
		publicationsTitle3A4.setBold(true);
		publicationsTitle3A4.setFontSize(10);
		publicationsTitle3A4.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A4.setText("3.A.4. BECAS Y AYUDAS");

		for (String s : map.get("BECAS")) {
			XWPFParagraph schollarshipParagraph = document.createParagraph();
			XWPFRun schollarship = schollarshipParagraph.createRun();
			schollarship.setFontSize(9);
			schollarship.setText(s);

		}

		XWPFParagraph publicationsTitle3A5Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3A5 = publicationsTitle3A5Paragraph.createRun();
		publicationsTitle3A5.setBold(true);
		publicationsTitle3A5.setFontSize(10);
		publicationsTitle3A5.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A5.setText("3.A.5. PREMIOS");

		for (String s : map.get("PREMIOS")) {
			XWPFParagraph awardParagraph = document.createParagraph();
			XWPFRun award = awardParagraph.createRun();
			award.setFontSize(9);
			award.setText(s);

		}

		XWPFParagraph publicationsTitle3A6Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3A6 = publicationsTitle3A6Paragraph.createRun();
		publicationsTitle3A6.setBold(true);
		publicationsTitle3A6.setFontSize(10);
		publicationsTitle3A6.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A6.setText("3.A.6. OTROS MÉRITOS ASOCIADOS A LA CALIDAD DE LA FORMACIÓN PREDOCTORAL");

		for (String s : map.get("OTROS PREDOCTORAL")) {
			XWPFParagraph predoctoralParagraph = document.createParagraph();
			XWPFRun predoctoral = predoctoralParagraph.createRun();
			predoctoral.setFontSize(9);
			predoctoral.setText(s);

		}

		XWPFParagraph publicationsTitle3A7Paragraph = document.createParagraph();
		XWPFRun publicationsTitle3A7 = publicationsTitle3A7Paragraph.createRun();
		publicationsTitle3A7.setBold(true);
		publicationsTitle3A7.setFontSize(10);
		publicationsTitle3A7.setUnderline(UnderlinePatterns.SINGLE);
		publicationsTitle3A7.setText("3.A.7. OTROS MÉRITOS ASOCIADOS A LA CALIDAD DE LA FORMACIÓN POSTDOCTORAL.");

		for (String s : map.get("OTROS POSTDOCTORAL")) {
			XWPFParagraph postdoctoralParagraph = document.createParagraph();
			XWPFRun postdoctoral = postdoctoralParagraph.createRun();
			postdoctoral.setFontSize(9);
			postdoctoral.setText(s);

		}

		XWPFParagraph publicationsTitle3BParagraph = document.createParagraph();
		XWPFRun publicationsTitle3B = publicationsTitle3BParagraph.createRun();
		publicationsTitle3B.setBold(true);
		publicationsTitle3B.setFontSize(12);
		publicationsTitle3B.setText("3.B OTROS MÉRITOS ASOCIADOS A LA FORMACIÓN ACADÉMICA.");

		for (String s : map.get("OTROS ACADÉMICA")) {
			XWPFParagraph academicParagraph = document.createParagraph();
			XWPFRun academic = academicParagraph.createRun();
			academic.setFontSize(9);
			academic.setText(s);

		}

		XWPFParagraph publicationsTitle4Paragraph = document.createParagraph();
		XWPFRun publicationsTitle4 = publicationsTitle4Paragraph.createRun();
		publicationsTitle4.setBold(true);
		publicationsTitle4.setFontSize(16);
		publicationsTitle4.setText(
				"4. EXPERIENCIA EN GESTIÓN Y ADMINISTRACIÓN EDUCATIVA, CIENTÍFICA, TECNOLÓGICA Y OTROS MÉRITOS.");

		XWPFParagraph publicationsTitle4AParagraph = document.createParagraph();
		XWPFRun publicationsTitle4A = publicationsTitle4AParagraph.createRun();
		publicationsTitle4A.setBold(true);
		publicationsTitle4A.setFontSize(12);
		publicationsTitle4A.setText(
				"4.A. DESEMPEÑO DE CARGOS UNIPERSONALES DE RESPONSABILIDAD EN GESTIÓN UNIVERSITARIA RECOGIDOS EN LOS ESTATUTOS DE LAS UNIVERSIDADES, O QUE HAYAN SIDO ASIMILADOS, U ORGANISMOS PÚBLICOS DE INVESTIGACIÓN DURANTE AL MENOS UN AÑO.");
		for (String s : map.get("EXPERIENCIA GESTIÓN")) {
			XWPFParagraph managementExperienceParagraph = document.createParagraph();
			XWPFRun managementExperience = managementExperienceParagraph.createRun();
			managementExperience.setFontSize(9);
			managementExperience.setText(s);
		}

		XWPFParagraph publicationsTitle4BParagraph = document.createParagraph();
		XWPFRun publicationsTitle4B = publicationsTitle4BParagraph.createRun();
		publicationsTitle4B.setBold(true);
		publicationsTitle4B.setFontSize(12);
		publicationsTitle4B.setText(
				"4.B. DESEMPEÑO DE PUESTOS EN EL ENTORNO EDUCATIVO, CIENTÍFICO O TECNOLÓGICO DENTRO DE LA ADMINISTRACIÓN GENERAL DEL ESTADO O DE LAS COMUNIDADES AUTÓNOMAS DURANTE AL MENOS UN AÑO.");

		for (String s : map.get("PUESTO ADMINISTRACIÓN")) {
			XWPFParagraph positionAdministrationParagraph = document.createParagraph();
			XWPFRun positionAdministration = positionAdministrationParagraph.createRun();
			positionAdministration.setFontSize(9);
			positionAdministration.setText(s);
		}

		XWPFParagraph publicationsTitle4CParagraph = document.createParagraph();
		XWPFRun publicationsTitle4C = publicationsTitle4CParagraph.createRun();
		publicationsTitle4C.setBold(true);
		publicationsTitle4C.setFontSize(12);
		publicationsTitle4C.setText("4.C. OTROS MÉRITOS RELACIONADOS CON LA EXPERIENCIA EN GESTIÓN Y ADMINISTRACIÓN.");

		for (String s : map.get("OTROS EXPERIENCIA")) {
			XWPFParagraph otherExperienceParagraph = document.createParagraph();
			XWPFRun otherExperience = otherExperienceParagraph.createRun();
			otherExperience.setFontSize(9);
			otherExperience.setText(s);
		}

		try {
			FileOutputStream fos = new FileOutputStream(new File(saveFile.getText() + ".docx"));
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

	private int[] do1A1(PdfReader reader, int pageIndex, int numPages) throws IOException {
		String cadena = "";
		for (int j = pageIndex; j <= numPages; j++) {
			String page = PdfTextExtractor.getTextFromPage(reader, j);
			String[] split = page.split("\n");
			for (int i = 0; i < split.length; i++) {
				if (!split[i].contains("1.A.2. PUBLICACIONES CIENTÍFICAS NO INDEXADAS")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("Artículo") && !split[i + x].contains("D.O.I.:")) {
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
							if (!split[i + x].contains("VOLUMEN:") && !split[i + x].contains("esde")) {
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

	private int[] do1A2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("VOLUMEN:") && !split[i + x].contains("esde")) {
								cadena += split[i + x] + " ";
							} else {
								break;
							}
						}
						map.get("PUBLICACIÓN NO INDEXADA").add(formatString(cadena));
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

	private int[] do1A3(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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

	private int[] do1A5(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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

	private int[] do1A6(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
								if (split[i + x + 1].contains("TIPO PARTICIPACIÓN")) {
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

	private int[] do1A7(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("ACTIVIDAD INVESTIGADORA") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("1.B. CALIDAD Y NÚMERO DE PROYECTOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS CALIDAD").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("1.B. CALIDAD Y NÚMERO DE PROYECTOS")) {
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

	private int[] do1B1(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("Proyecto de investigación")
									&& !split[i + x].contains("TIPO DE PARTICIPACIÓN:")
									&& !split[i + x].contains("Contrato de investigación")
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

	private int[] do1B2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("1.C. CALIDAD DE LA TRANSFERENCIA")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS NÚMERO").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("1.C. CALIDAD DE LA TRANSFERENCIA")) {
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

	private int[] do1C2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("1.C.2. TRANSFERENCIA DE CONOCIMIENTO") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("1.C.3. OTROS MÉRITOS RELACIONADOS")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("TRANSFERENCIA").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("1.C.3. OTROS MÉRITOS RELACIONADOS")) {
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

	private int[] do1C3(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("1.D. MOVILIDAD DEL PROFESORADO")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS TRANSFERENCIA").add(formatString(cadena));
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
						map.get("OTROS TRANSFERENCIA").add(formatString(cadena));
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

	private int[] do1D1(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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

	private int[] do1D2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("ACTIVIDAD INVESTIGADORA") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("2. ACTIVIDAD DOCENTE O PROFESIONAL")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS INVESTIGACIÓN").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("2. ACTIVIDAD DOCENTE O PROFESIONAL")) {
					if (!cadena.equals("")) {
						map.get("OTROS INVESTIGACIÓN").add(formatString(cadena));
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

	private int[] do2A1(PdfReader reader, int pageIndex, int numPages, int nexLine) throws IOException {
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

	private int[] do2A2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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

	private int[] do2A3(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("2.A.4. OTROS MÉRITOS RELACIONADOS")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("DIRECCIÓN PROYECTO").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("2.A.4. OTROS MÉRITOS RELACIONADOS")) {
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

	private int[] do2A4(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("2.A.4. OTROS MÉRITOS RELACIONADOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("2.B. CALIDAD DE LA ACTIVIDAD")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("OTROS DOCENTE").add(formatString(cadena));
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

	private int[] do2B1(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("2.B.2. MATERIAL DOCENTE ORIGINAL")) {
								cadena += split[i + x] + "\n";
							} else {
								map.get("EVALUACIONES POSITIVAS").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("2.B.2. MATERIAL DOCENTE ORIGINAL")) {
					if (!cadena.equals("")) {
						map.get("EVALUACIONES POSITIVAS").add(formatString(cadena));
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

	private int[] do2B2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (!split[i].contains("2.B.3. PROYECTOS DE INNOVACIÓN DOCENTE")) {
					if (split[i].contains("TÍTULO")) {
						cadena += split[i - 1] + " ";
						for (int x = 1; i + x < split.length; x++) {
							if (!split[i + x].contains("Editor") && !split[i + x].equals("")
									&& !split[i + x].contains("CLAVE:") && !split[i + x].contains("Apuntes")
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

	private int[] do2B3(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
						map.get("PROYECTO INNOVACIÓN").add(formatString(cadena));
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

	private int[] do2B4(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("2.B.4. OTROS MÉRITOS RELACIONADOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("2.C CALIDAD DE LA FORMACIÓN DOCENTE")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS CALIDAD ACTIVIDAD").add(formatString(cadena));
								int[] index = new int[2];
								index[0] = j;
								index[1] = i + x;
								return index;
							}
						}
					}
					break;
				} else if (split[i].contains("2.C CALIDAD DE LA FORMACIÓN DOCENTE")) {
					if (!cadena.equals("")) {
						map.get("OTROS CALIDAD ACTIVIDAD").add(formatString(cadena));
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

	private int[] do2C1(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
						map.get("PARTICIPACIÓN PONENTE").add(formatString(cadena));
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

	private int[] do2C2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
						map.get("PARTICIPACIÓN ASISTENTE").add(formatString(cadena));
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

	private int[] do2C3(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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

	private int[] do2C4(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("2.C.4.OTROS MÉRITOS RELACIONADOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("2.D CALIDAD Y DEDICACIÓN")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS FORMACIÓN").add(formatString(cadena));
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

	private int[] do2D1(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("En las áreas clínicas de Ciencias de la Salud")) {
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

	private int[] do2D2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("2.D.2. EVALUACIONES POSITIVAS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("2.E. OTROS MÉRITOS RELACIONADOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("EVALUACIONES ACTIVIDAD").add(formatString(cadena));
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
						map.get("EVALUACIONES ACTIVIDAD").add(formatString(cadena));
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

	private int[] do2E(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("2.E. OTROS MÉRITOS RELACIONADOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("3. FORMACIÓN ACADÉMICA")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS MÉRITOS").add(formatString(cadena));
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

	private int[] do3A1(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
						map.get("TITULACIÓN UNIVERSITARIA").add(formatString(cadena));
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

	private int[] do3A2(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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

	private int[] do3A3(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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

	private int[] do3A4(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
								if (!split[i + x].contains("ENTIDAD FINANCIADORA")) {
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

	private int[] do3A5(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
							if (!split[i + x].contains("3.A.6. OTROS MÉRITOS ASOCIADOS")) {
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

	private int[] do3A6(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("3.A.6. OTROS MÉRITOS ASOCIADOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("3.A.7. OTROS MÉRITOS ASOCIADOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS PREDOCTORAL").add(formatString(cadena));
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

	private int[] do3A7(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
				if (split[i].contains("3.A.7. OTROS MÉRITOS ASOCIADOS") || reading == true) {
					reading = true;
					for (int x = 1; i + x < split.length; x++) {
						if (!split[i + x].equals("")) {
							if (!split[i + x].contains("3.B OTROS MÉRITOS")) {
								cadena += split[i + x] + " ";
							} else {
								map.get("OTROS POSTDOCTORAL").add(formatString(cadena));
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

	private int[] do4A(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
						map.get("EXPERIENCIA GESTIÓN").add(formatString(cadena));
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

	private int[] do4B(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
						map.get("PUESTO ADMINISTRACIÓN").add(formatString(cadena));
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

	private int[] do4C(PdfReader reader, int pageIndex, int numPages, int lineIndex) throws IOException {
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
