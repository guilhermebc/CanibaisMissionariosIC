import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("Problema dos Missionários e Canibais");	
		Estado estadoInicial = new Estado (3, 3, Posicao.ESQUERDA, 0, 0);
		executaBusca(estadoInicial);

	}

	private static void executaBusca(Estado initialState) {
		BuscaProfundidade search = new BuscaProfundidade();
		Estado solution = search.exec(initialState);
		printSolution(solution);
	}

	private static void printSolution(Estado solucao) {
		if (null == solucao) {
			System.out.print("\nNenhuma solucao encontrada.");
		} else {
			System.out.println("\nSolucao (canibalEsquerda,missionarioEsquerda,barco,canibalDireita,missionarioDireita): ");
			List<Estado> path = new ArrayList<Estado>();
			Estado estado = solucao;
			while(null!=estado) {
				path.add(estado);
				estado = estado.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				estado = path.get(i);
				if (estado.isGoal()) {
					System.out.print(estado.toString());
				} else {
					System.out.print(estado.toString() + " -> ");
				}
			}
			System.out.println("\nDepth: " + depth);
		}
	}
}
