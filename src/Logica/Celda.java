package Logica;

public class Celda {
	private Integer valor;
	private EntidadGrafica entidadG;
	private int fila, columna;
	private boolean modificable ;// para saber si puedo modificarla en el juego, es decir si es una celda con la que se inicializo el juego 
	
	public Celda() {
		this.modificable = true;
		this.valor = null;
		this.entidadG = new EntidadGrafica();
		this.columna = this.fila = 0;
	}
	
	public void actualizar() {
		if(this.valor != null && this.valor + 1 < this.getCantElem()) {
			this.valor++;
		}else {
			this.valor = 0;
		}
		entidadG.actualizar(this.valor);
	}

	public int getCantElem() {
		return this.entidadG.getNumeros().length;
	}
	
	public Integer getValor() {
		return this.valor;
	}
	
	public void setValor(Integer valor) {
		if(valor != null && valor < this.getCantElem() ) {
			this.valor = valor;
			this.entidadG.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	} 
	
	public void setModificable(boolean m) {
		this.modificable = m;
	}
	
	public int getFila() {
		return this.fila;
	}
	
	public int getCol() {
		return this.columna;
	}
	
	public void setFila(int f) {
		this.fila = f;
	}
	
	public void setCol(int c) {
		this.columna = c;
	}
	
	public EntidadGrafica getEntidadG() {
		return this.entidadG;
	}
	
	public boolean modificable() {
		return this.modificable;
	}
	/*
	public boolean equals(Celda c) {
		return this.entidadG.equals(c.getEntidadG());
	}*/
}
