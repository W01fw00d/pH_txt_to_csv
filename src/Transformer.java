package pH_txt_to_csv;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class Transformer {

	private JFrame frmSintetitzadorDinformaci;
	
	final static JComboBox<String> comboBox = new JComboBox<String>();
	final static JTextField txtArxiu = new JTextField();
	final static JLabel lblResultat = new JLabel("");
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transformer window = new Transformer();
					window.frmSintetitzadorDinformaci.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Transformer() {
		initialize();
		
		try {
			
			mostrarArxiusDisponibles();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSintetitzadorDinformaci = new JFrame();
		frmSintetitzadorDinformaci.setTitle("Sintetitzador d'informació");
		frmSintetitzadorDinformaci.setBounds(100, 100, 450, 300);
		frmSintetitzadorDinformaci.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSintetitzadorDinformaci.getContentPane().setLayout(null);

		comboBox.setModel(new DefaultComboBoxModel<String>(/*new String[] { "Arxiu 1", "Arxiu 2" }*/));
		comboBox.setBounds(173, 36, 164, 27);
		frmSintetitzadorDinformaci.getContentPane().add(comboBox);
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	possarNomDefault();
		    }
		});

		txtArxiu.setText("Arxiu 1");
		txtArxiu.setBounds(173, 83, 164, 26);
		frmSintetitzadorDinformaci.getContentPane().add(txtArxiu);
		txtArxiu.setColumns(10);

		JLabel lblArxiuDentrada = new JLabel("Arxiu d'entrada");
		lblArxiuDentrada.setBounds(56, 40, 123, 16);
		frmSintetitzadorDinformaci.getContentPane().add(lblArxiuDentrada);

		JLabel lblArxiuDeSortida = new JLabel("Arxiu de sortida");
		lblArxiuDeSortida.setBounds(56, 88, 101, 16);
		frmSintetitzadorDinformaci.getContentPane().add(lblArxiuDeSortida);

		JButton btnCrearArxiu = new JButton("Crear arxiu");
		btnCrearArxiu.setBounds(130, 206, 187, 48);
		frmSintetitzadorDinformaci.getContentPane().add(btnCrearArxiu);
		
		btnCrearArxiu.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	Object arxiuSeleccionat = comboBox.getSelectedItem();
		    	escriureCsv( llegirTxt(arxiuSeleccionat.toString() + ".txt") );
		    }
		});
		lblResultat.setHorizontalAlignment(SwingConstants.CENTER);

		lblResultat.setBounds(111, 146, 238, 16);
		frmSintetitzadorDinformaci.getContentPane().add(lblResultat);
		
		JLabel lbltxt = new JLabel(".txt");
		lbltxt.setBounds(349, 40, 61, 16);
		frmSintetitzadorDinformaci.getContentPane().add(lbltxt);
		
		JLabel lblcsv = new JLabel(".csv");
		lblcsv.setBounds(349, 88, 61, 16);
		frmSintetitzadorDinformaci.getContentPane().add(lblcsv);
	}

	/////// LOGICA


	
	/**
	 * Mostra al ComboBox els arxius .txt detectacts a "entrada"
	 */
	public static void mostrarArxiusDisponibles() throws IOException {

		File carpeta = new File("entrada/");
		String[] arxiu_array = carpeta.list();
		String tipus_arxiu;

		for (int i = 0; i < arxiu_array.length; i++) {
			
			tipus_arxiu = arxiu_array[i].substring( arxiu_array[i].length() - 4, arxiu_array[i].length() );
			
			if ( tipus_arxiu.equals(".txt") || tipus_arxiu.equals(".TXT") ){
				comboBox.addItem(arxiu_array[i].replaceAll(".txt", "").replace(".TXT", ""));
			}
		}
	}
	
	
	/**
	 * Coloca al cuadre de texte el nom de l'arxiu original
	 */
	public static void possarNomDefault(){
		
		Object arxiuSeleccionat = comboBox.getSelectedItem();
		txtArxiu.setText(arxiuSeleccionat.toString());
	}

	/**
	 * Llegeix l'arxiu .txt original linea per linea i guarda les dades que se necessiten
	 */
	public static ArrayList<String> llegirTxt(String nomArxiu) {

		File path_arxiu = new File("entrada/" + nomArxiu);

		//De las 19 lineas que tiene cada entrada, queremos la linea en pos 2 y 4. A partir de la 14 se pueden ignorar 5 lineas que son espacios.
		
		ArrayList<String> puntos_info = new ArrayList<String>();
		
		try {

			FileReader fr = new FileReader(path_arxiu);
			BufferedReader br = new BufferedReader(fr);

			String linia = "";
			
			String punto_info;
			
			int num_lineas = 37;
			
			while ((linia = br.readLine()) != null) {
				//System.out.println(linia);
				punto_info = "";
				
				for (int i = 1; i <= num_lineas; i++) {
					
					linia = br.readLine();
					
					//System.out.println(i + ": " + linia);
					
					//Hora
					if ( (i == 4) && (linia != null) ){
						//System.out.println(linia);
						punto_info += linia;
					
					//pH
					}else if ( (i == 8) && (linia != null) ){
						//System.out.println(linia);
						punto_info += ";" + linia.substring(0, linia.length() - 6).replace('.', ',');
	
					}
				}
				//System.out.println(punto_info);
				
				if (linia == null){
					break;
				}
				
				puntos_info.add(punto_info);
				
			}
			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return puntos_info;
	}
	
	/**
	 * Genera l'arxiu .csv de sortida
	 */
	public static void escriureCsv(ArrayList<String> puntos_info){
		
		String hora = "";
		String nova_hora = "";
		String nom_sortida = txtArxiu.getText();
		String [] punto_info;
		
		//Resultat final que s'escriu al .csv
		String puntos = "sep=;\nHora;Punto;pH\n";
		
		int punto = 1;
		
		int puntos_info_size = puntos_info.size() - 1;
		
		//El .txt tiene los puntos en orden cronológico invertido, hay que reordenarlo
		for (int i = puntos_info_size; i >= 0; i--) {
			
			punto_info = puntos_info.get(i).split(";");
			
			nova_hora = punto_info[0];
			
			if (i == puntos_info_size){
				
				hora = nova_hora;
			
			}else if (hora.equals(nova_hora)){
				
				punto++;
			}else{
				
				punto = 1;
				hora = nova_hora;
			}
			
			if (punto == 1){
				puntos += hora + ";[" + punto + "];" + punto_info[1] + "\n";
			}else{
				puntos += hora + ";" + punto + ";" + punto_info[1] + "\n";
			}
			
		}
		
		try {
			FileWriter fw = new FileWriter("sortida/" + nom_sortida + ".csv");
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(puntos);

			bw.close();
			fw.close();
			
			lblResultat.setText("[Arxiu "+nom_sortida+".csv creat]");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
