import java.util.List;

// based on the depth-limited search algorithm present on the 3o Edition of the
// "Artificial Intelligence A Modern Approach".
public class BuscaProfundidade {

	public Estado exec(Estado initialState) {
		int limit = 20;
		return buscaRecursiva(initialState, limit);
	}

	private Estado buscaRecursiva(Estado estado, int limit) {
		if (estado.isGoal()) {
			return estado;
		} else if (limit == 0) {
			return null;
		} else {
			List<Estado> successors = estado.generateSuccessors();
			for (Estado child : successors) {
				Estado result = buscaRecursiva(child, limit - 1);
				if (null != result) {
					return result;
				}
			}
			return null;
		}
	}
}
