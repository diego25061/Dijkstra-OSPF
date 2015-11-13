
import java.util.ArrayList;

public class Calculador {
	
	
	private ArrayList <AristaVisible> listaAristas;
	private ArrayList <NodoVisible> listaNodos;
 
	
	public Calculador(){
	}
	
	public void darAristas(ArrayList <AristaVisible> aristas){
		listaAristas = aristas;
	}

	
	public void darNodos(ArrayList <NodoVisible> nodos){
		listaNodos = nodos;
		llenarListaPesosNodos();
		llenarListaNodosVisitados();
	}
	
	
	public void llenarListaNodosVisitados(){
		nodoFueVisitado = new boolean[listaNodos.size()];
		for (int i = 0 ; i<listaNodos.size(); i ++){
			nodoFueVisitado[i] = false;
		}
	}
	
	public void llenarListaPesosNodos(){
		listaDistanciasNodos = new int[listaNodos.size()];
	
		for (int i = 0 ; i<listaNodos.size(); i ++){
			listaDistanciasNodos[i] = Integer.MAX_VALUE;
		}
	}
	
	//unvisited set
	private boolean [] nodoFueVisitado;
	
	//tentative distance
	private int listaDistanciasNodos[] ;
	
	//los nodos que ya no seran usados tendran valor de -1
	public int getNodoConMenorDistancia(){
		int pesoMenor=Integer.MAX_VALUE;
		int nodo= -1;
		for (int i = 0 ; i< listaNodos.size();i++){
			if (listaDistanciasNodos[i]<=pesoMenor && listaDistanciasNodos[i]!= -1){
				pesoMenor =listaDistanciasNodos[i];
				nodo = i;
			}
		}
		return nodo;
	}
	
	public ArrayList <Integer> getNodosVecinos(int nodo){
		ArrayList <Integer> lista = new ArrayList<Integer>();
		for(int i = 0 ; i < listaAristas.size(); i++){
			if (listaAristas.get(i).nodoI==nodo)
				lista.add(listaAristas.get(i).nodoF);
		}
		return lista;
	}
	
	public ArrayList <Integer> getAristasHaciaVecinos(int nodo){
		ArrayList <Integer> lista = new ArrayList<Integer>();
		for(int i = 0 ; i < listaAristas.size(); i++){
			if (listaAristas.get(i).nodoI==nodo)
				lista.add(i);
		}
		return lista;
	}

	
	void invertirArrayListInteger(ArrayList<Integer> lista){
		ArrayList<Integer> aux = new ArrayList<Integer>();
		aux.addAll(lista);

		lista.clear();
		
		for (int i = 0; i< aux.size(); i++){
			lista.add(i,aux.get(aux.size()-i -1));
		}		
	}
	
	int getProcedenciaMenor (int [][]array , int destino, int limite1){
		int n = Integer.MAX_VALUE;
		int a = Integer.MAX_VALUE;
		for (int i=0 ;i< limite1 ; i++){
			if ( array[i][destino] < n ){
				n= array[i][destino];
				a = i;
			}
		}
		return a;
	}
	
	public static int ultimaDistancia;
	
	public ArrayList <Integer> calcularCamino(int nodoI, int nodoF){
		ArrayList <Integer> listaRuta = new ArrayList <Integer>();
		//ArrayList <Integer> listaPesosRuta = new ArrayList <Integer>();
		//lista.clear();
		this.listaDistanciasNodos[nodoI] = 0;
		int cont=0;
		
		int [][] distancia = new int [listaNodos.size()] [listaNodos.size()];
		
		for(int i = 0; i<listaNodos.size();i++)
			for (int j = 0 ; j <listaNodos.size();j++)
				distancia[i][j]= Integer.MAX_VALUE;
		

		//ArrayList <Integer> tramos = new ArrayList <Integer>();
		//ArrayList <Integer> distTramo = new ArrayList <Integer>();
		
		//iteracion
		//listaRuta.add(nodoI);
		do{
			
		int nodoActual = getNodoConMenorDistancia();
		ArrayList <Integer> aristasVecinas = getAristasHaciaVecinos (nodoActual);
		
		/*
		int [] predecesor = new int [ this.listaNodos.size()];
		
		for (int i = 0 ;i < listaNodos.size(); i++){
			predecesor [i] = -1;
		}
*/
		for(int i = 0;i<aristasVecinas.size();i++){
			
			System.out.println(
				"nodo actual: "+nodoActual +
				" | DISTANCIA a nodo "+ listaAristas.get(aristasVecinas.get(i)).nodoF +
				" = " + (listaAristas.get(aristasVecinas.get(i)).peso+listaDistanciasNodos[nodoActual]));
			
			distancia [nodoActual] [listaAristas.get(aristasVecinas.get(i)).nodoF] = (listaAristas.get(aristasVecinas.get(i)).peso+listaDistanciasNodos[nodoActual]);

			
			//tramos.add( listaAristas.get(aristasVecinas.get(i)).nodoF);
			//distTramo.add(listaAristas.get(aristasVecinas.get(i)).peso+listaDistanciasNodos[nodoActual]);
			
			int nodoFinalAristaActual = this.listaAristas.get( aristasVecinas.get(i) ).nodoF ;
			int nodoInicialAristaActual = this.listaAristas.get( aristasVecinas.get(i) ).nodoI ;
			// ES MAYOR O MAYOR IGUAAAAAAAAAAAAAAAAALLL----- V----- ?
			if(listaDistanciasNodos[ nodoFinalAristaActual ] > listaDistanciasNodos[ nodoInicialAristaActual ] + listaAristas.get( aristasVecinas.get(i) ).peso)
			listaDistanciasNodos[ nodoFinalAristaActual ] = listaDistanciasNodos[ nodoInicialAristaActual ] + listaAristas.get( aristasVecinas.get(i) ).peso;

		}
		
		//listaRuta.add(nodoActual);
		//listaPesosRuta.add(listaDistanciasNodos[nodoActual]);
		
		listaDistanciasNodos[nodoActual] = -1;
		
		cont++;
		}while (cont<this.listaNodos.size() && listaDistanciasNodos[nodoF] != -1);	
		
		/*
		listaRuta.add(nodoF);
		
		int n = nodoF;
		do{
			System.out.println(""+ getProcedenciaMenor(distancia, n , listaNodos.size()));

			listaRuta.add(getProcedenciaMenor(distancia, n , listaNodos.size()));
			n= getProcedenciaMenor(distancia, n , listaNodos.size());
		}while(n!= 0);
		*/
		
		if(nodoI!=nodoF && getProcedenciaMenor(distancia, nodoF , listaNodos.size())!=Integer.MAX_VALUE)
			ultimaDistancia = distancia [getProcedenciaMenor(distancia, nodoF , listaNodos.size())] [nodoF]; 
		
		listaRuta.add(nodoF);
		int n = nodoF;
		cont = 0;
		boolean salir = false;
		while(n!= nodoI && !salir){
			System.out.println(""+ getProcedenciaMenor(distancia, n , listaNodos.size()));

			listaRuta.add(getProcedenciaMenor(distancia, n , listaNodos.size()));
			
			n= getProcedenciaMenor(distancia, n , listaNodos.size());
			cont++;
			
			if ( n == Integer.MAX_VALUE ){
				salir = true;
				System.out.println("Imposible llegar al destino");
				return null;
			}
			
			/*
			if(cont == listaNodos.size()){
				salir = true;
				System.out.println("Imposible llegar al destino B");
			}*/
		}
		
		invertirArrayListInteger(listaRuta);
		
		/*
		int n = nodoF;
		int a= 0;
		int index= 0;
		//listaRuta.ensureCapacity(tramos.size());
		int[] l = new int[123];
		boolean haySolucion = false;
		do{
			double min = Double.POSITIVE_INFINITY;
			a = 0;
			boolean b = true;
			for (int i = 0; i < tramos.size() ;i ++){
				
				if(tramos.get(i)==n){
					System.out.println("Tramo "+i+" va hasta el nodo "+ tramos.get(i)+ " y pesa "+distTramo.get(i));
					haySolucion = true;
					
					if(distTramo.get(i) < min){
						min = distTramo.get(i);
						a= tramos.get(i);
						index=(i);
					}
					
				}
			}
			if(!haySolucion){
				System.out.println("Imposible llegar al destino");
				n = 0;
			}else{
				//listaRuta.add(index,a);
				l[index] = a;
				n--;
			}
		}
		while (n != -1);
		

		/*
		listaRuta.clear();
		for (int i= 0 ; i< 12;i++)
			listaRuta.add(l[i]);
*/
		
		
		//listaRuta.add(nodoI);
		
		/*
		//iteracion
		do{
			if(listaDistanciasNodos[nodoF] != -1){
				System.out.println("Se ha llegado al final de la ruta");

				return listaRuta;
			}else
				if (cont<this.listaNodos.size()){
					System.out.println("Imposible llegar al destino");
					return listaRuta;
				}else{
						
					int nodoActual = getNodoConMenorDistancia();
					ArrayList <Integer> aristasVecinas = getAristasHaciaVecinos (nodoActual);
		
					for(int i = 0;i<aristasVecinas.size();i++){
			
						System.out.println(
								"nodo actual: "+nodoActual +
								" | DISTANCIA a nodo "+ listaAristas.get(aristasVecinas.get(i)).nodoF +
								" = " +listaAristas.get(aristasVecinas.get(i)).peso+listaDistanciasNodos[nodoActual]);
		
						int nodoFinalAristaActual = this.listaAristas.get( aristasVecinas.get(i) ).nodoF ;
						int nodoInicialAristaActual = this.listaAristas.get( aristasVecinas.get(i) ).nodoI ;
						// ES MAYOR O MAYOR IGUAAAAAAAAAAAAAAAAALLL----- V----- ?
						if(listaDistanciasNodos[ nodoFinalAristaActual ] > listaDistanciasNodos[ nodoInicialAristaActual ] + listaAristas.get( aristasVecinas.get(i) ).peso)
							listaDistanciasNodos[ nodoFinalAristaActual ] = listaDistanciasNodos[ nodoInicialAristaActual ] + listaAristas.get( aristasVecinas.get(i) ).peso;

		
					}
		
		
					listaRuta.add(nodoActual);
					listaPesosRuta.add(listaDistanciasNodos[nodoActual]);
					listaDistanciasNodos[nodoActual] = -1;
					cont++;
			
				}

		}while (true);	
*/
		
		/*
		//iteracion
		do{
			if(listaDistanciasNodos[nodoF] != -1){
				if (cont!=this.listaNodos.size()){
				 
					
					int nodoActual = getNodoConMenorDistancia();
					ArrayList <Integer> aristasVecinas = getAristasHaciaVecinos (nodoActual);
		
					for(int i = 0;i<aristasVecinas.size();i++){
			
						System.out.println(
								"nodo actual: "+nodoActual +
								" | DISTANCIA a nodo "+ listaAristas.get(aristasVecinas.get(i)).nodoF +
								" = " +listaAristas.get(aristasVecinas.get(i)).peso+listaDistanciasNodos[nodoActual]);
		
						int nodoFinalAristaActual = this.listaAristas.get( aristasVecinas.get(i) ).nodoF ;
						int nodoInicialAristaActual = this.listaAristas.get( aristasVecinas.get(i) ).nodoI ;
						// ES MAYOR O MAYOR IGUAAAAAAAAAAAAAAAAALLL----- V----- ?
						if(listaDistanciasNodos[ nodoFinalAristaActual ] > listaDistanciasNodos[ nodoInicialAristaActual ] + listaAristas.get( aristasVecinas.get(i) ).peso)
							listaDistanciasNodos[ nodoFinalAristaActual ] = listaDistanciasNodos[ nodoInicialAristaActual ] + listaAristas.get( aristasVecinas.get(i) ).peso;

		
					}
		
		
					listaRuta.add(nodoActual);
					listaPesosRuta.add(listaDistanciasNodos[nodoActual]);
					listaDistanciasNodos[nodoActual] = -1;
					cont++;
				}else{
					System.out.println("Imposible llegar al destino");
					return listaRuta;
				}
				
			}else{
				System.out.println("Se ha llegado al final de la ruta");

				return listaRuta;
			
				}

		}while (true);	
		*/
		return listaRuta;
	}
	
}






