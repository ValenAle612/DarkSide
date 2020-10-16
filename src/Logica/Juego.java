package Logica;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;

import Logica.Celda;
public class Juego {
	private Celda [][] tablero;
	private LinkedList<Celda> repetidos;
	
	public Juego(String sudoku) {
		tablero=new Celda[9][9];
		
		this.inicializar(sudoku);//sudoku
	}
	public void inicializar(String sudoku) {
		
			repetidos = new LinkedList<Celda>();
		
			int Matriz[][] = crearMatriz(sudoku);
			boolean MatrizValida = this.validarMatriz(Matriz);
			
			for(int i=0; i < 9 ; i++) {//INICIALIZO LA MATRIZ
				for(int j=0; j < 9 ; j++) {
					tablero[i][j] = new Celda();
					tablero[i][j].setFila(i);
					tablero[i][j].setCol(j);
				}
			}
			
			
			for (int i =0; i<9  && MatrizValida; i++) {
				for (int j =0; j<9; j++) {
					
					Random rand = new Random();
					int value = rand.nextInt(3);

					if (value == 0){

						int valor = rand.nextInt(9);
						
						tablero[i][valor].setValor(Matriz[i][valor]-1);
						tablero[i][valor].setModificable(false);
					}
				}
			}
			
	}
	
	private int[][] crearMatriz(String path){
		
		int iMatriz=0, jMatriz=0, pos=0;
		
		int [][] Matriz = new int[9][9];
		
		
		
			try {
		
					File archivo = new File (path);
					FileReader fr=new FileReader(archivo);
					BufferedReader br=new BufferedReader(fr);
					String linea;
		
					while( (linea=br.readLine()) != null && iMatriz<9 ) {
						jMatriz=0;
						pos=0;
		
						while( pos < linea.length() ) {
				
							if( linea.charAt(pos) != ' ' && jMatriz<9 ) {
								Matriz[iMatriz][jMatriz] = Integer.parseInt(""+linea.charAt(pos));
								jMatriz++;
							} 
							pos++;
				
						}

						iMatriz++;
			
					}	
			
					br.close();
					fr.close();


			}catch(	IOException e ) {
				e.printStackTrace();
				e.getMessage();

			}
		
		
		return Matriz;
	}	
	private boolean validarMatriz(int [][]Matriz) {
		boolean validarMatriz, vF=true, vC=true;
		
		for(int i=0; i<9; i ++) {
			for(int j=0; j<9; j++) {
				System.out.print(Matriz[i][j]+" ");
			}
			System.out.println("");
		}
		
		
		for(int i=0; i < Matriz.length && vF; i++) { //validarFilas
			vF = vF && validarFilas(Matriz[i]);
		}
		
			vC = validarCol(Matriz); 
			
		return vF && vC;
		//validarFilas(Matriz[][]) && validarCol(Matriz[][]) && validarCuadrante(Matriz[][]);
				
	}
	
	private boolean validarCol(int Matriz[][] ) {
		boolean vC=true;
		int actual;
		for(int j=0; j < Matriz.length && vC; j++) {
			for(int i=0; i < Matriz.length && vC; i++) {
				actual = Matriz[i][j];
				
				for(int k=0; k < Matriz.length && vC; k++) {
					
					if( i != k ) {
						vC = ( actual != Matriz[k][j] );
					}
				}// end for k 
			}//end for i
		}//end for j
		
		return vC;
	}
	
	private boolean validarFilas(int Matriz[]) {
		boolean vF=true;
		int actual;
		for(int i=0; i<Matriz.length; i++) {
			actual = Matriz[i];
			for(int j=0; j < Matriz.length; j++) {
				if( i != j ) 
					vF = ( actual != Matriz[j] );
			}
		}
		
		return vF;
		
	}
	
	public boolean verificar() {
		boolean verif = true;
		boolean primera = true;
		for(int i=0; i<tablero.length; i++) {//filas
		
			for(int j=0; j<tablero.length; j++) {
				primera  = true;
				for(int k=j+1; k<tablero.length; k++) {
						if(tablero[i][k].getEntidadG().getIndexActual() != tablero[i][j].getEntidadG().getIndexActual())
							verif = verif && true;
						else {
							verif = verif && false;
							if(tablero[i][j].getEntidadG().getIndexActual() != 9) {
								this.repetidos.addLast(tablero[i][k]);
								if(primera) { // agrego la que comparo si esta repetida a la lista
									this.repetidos.addLast(tablero[i][j]);
									primera = false;
								}
							}
						}
				}//end for k
			}//end for j
		}//end for i
		
		for(int j=0; j<9; j++){//columnas
			for(int i=0; i<9 ; i++) {
				primera  = true;
				for(int k=i+1; k<9 ; k++) {
						if(tablero[k][j].getEntidadG().getIndexActual() != tablero[i][j].getEntidadG().getIndexActual()) {
							verif= verif && true;
						}else {
								verif = verif && false;
								if(tablero[i][j].getEntidadG().getIndexActual() != 9) {
									this.repetidos.addLast(tablero[k][j]);
									if(primera) { // agrego la que comparo si esta repetida a la lista
										this.repetidos.addLast(tablero[i][j]);
										primera = false;
								}
							}
						}
				}//end for k
			}//end for i
		}//end for j
	
		
		
		return verif;
	}
	
	public void vaciarRepetidos() {
		this.repetidos.clear();
	}
	
	public LinkedList<Celda> getRepetidos(){
		return this.repetidos;
	}
	
	public void accionar(Celda c) {
		c.actualizar();
	}
	public int getCantFilas() {
		return 9;
	}
	public int getCantCol() {
		return 9;
	}
	
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
}
