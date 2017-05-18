import java.util.ArrayList;
import java.util.List;

enum Posicao {DIREITA, ESQUERDA}

public class Estado {

	private int canibalEsquerda, missionarioEsquerda, canibalDireita, missionarioDireita;
	private Posicao barco;

	private Estado parentState;

	public Estado(int canibalEsquerda, int missionarioEsquerda, Posicao barco,
			int canibalDireita, int missionarioDireita) {
		this.canibalEsquerda = canibalEsquerda;
		this.missionarioEsquerda = missionarioEsquerda;
		this.barco = barco;
		this.canibalDireita = canibalDireita;
		this.missionarioDireita = missionarioDireita;
	}

	public boolean isGoal() {
		return canibalEsquerda == 0 && missionarioEsquerda == 0;
	}

	public boolean isValid() {
		if (missionarioEsquerda >= 0 && missionarioDireita >= 0 && canibalEsquerda >= 0 && canibalDireita >= 0
	               && (missionarioEsquerda == 0 || missionarioEsquerda >= canibalEsquerda)
	               && (missionarioDireita == 0 || missionarioDireita >= canibalDireita)) {
			return true;
		}
		return false;
	}

	public List<Estado> generateSuccessors() {
		List<Estado> sucessor = new ArrayList<Estado>();
		if (barco == Posicao.ESQUERDA) {
			testAndAdd(sucessor, new Estado(canibalEsquerda, missionarioEsquerda - 2, Posicao.DIREITA,
					canibalDireita, missionarioDireita + 2)); 
			testAndAdd(sucessor, new Estado(canibalEsquerda - 2, missionarioEsquerda, Posicao.DIREITA,
					canibalDireita + 2, missionarioDireita)); 
			testAndAdd(sucessor, new Estado(canibalEsquerda - 1, missionarioEsquerda - 1, Posicao.DIREITA,
					canibalDireita + 1, missionarioDireita + 1));
			testAndAdd(sucessor, new Estado(canibalEsquerda, missionarioEsquerda - 1, Posicao.DIREITA,
					canibalDireita, missionarioDireita + 1)); 
			testAndAdd(sucessor, new Estado(canibalEsquerda - 1, missionarioEsquerda, Posicao.DIREITA,
					canibalDireita + 1, missionarioDireita));
		} else {
			testAndAdd(sucessor, new Estado(canibalEsquerda, missionarioEsquerda + 2, Posicao.ESQUERDA,
					canibalDireita, missionarioDireita - 2)); 
			testAndAdd(sucessor, new Estado(canibalEsquerda + 2, missionarioEsquerda, Posicao.ESQUERDA,
					canibalDireita - 2, missionarioDireita)); 
			testAndAdd(sucessor, new Estado(canibalEsquerda + 1, missionarioEsquerda + 1, Posicao.ESQUERDA,
					canibalDireita - 1, missionarioDireita - 1));
			testAndAdd(sucessor, new Estado(canibalEsquerda, missionarioEsquerda + 1, Posicao.ESQUERDA,
					canibalDireita, missionarioDireita - 1)); 
			testAndAdd(sucessor, new Estado(canibalEsquerda + 1, missionarioEsquerda, Posicao.ESQUERDA,
					canibalDireita - 1, missionarioDireita));
		}
		return sucessor;
	}

	private void testAndAdd(List<Estado> successors, Estado newState) {
		if (newState.isValid()) {
			newState.setParentState(this);
			successors.add(newState);
		}
	}

	public int getCannibalLeft() {
		return canibalEsquerda;
	}

	public void setCannibalLeft(int cannibalLeft) {
		this.canibalEsquerda = cannibalLeft;
	}

	public int getMissionaryLeft() {
		return missionarioEsquerda;
	}

	public void setMissionaryLeft(int missionaryLeft) {
		this.missionarioEsquerda = missionaryLeft;
	}

	public int getCannibalRight() {
		return canibalDireita;
	}

	public void setCannibalRight(int cannibalRight) {
		this.canibalDireita = cannibalRight;
	}

	public int getMissionaryRight() {
		return missionarioDireita;
	}

	public void setMissionaryRight(int missionaryRight) {
		this.missionarioDireita = missionaryRight;
	}

	public void goToLeft() {
		barco = Posicao.ESQUERDA;
	}

	public void goToRight() {
		barco = Posicao.DIREITA;
	}

	public boolean isOnLeft() {
		return barco == Posicao.ESQUERDA;
	}

	public boolean isOnRigth() {
		return barco == Posicao.DIREITA;
	}

	public Estado getParentState() {
		return parentState;
	}

	public void setParentState(Estado parentState) {
		this.parentState = parentState;
	}

	@Override
	public String toString() {
		if (barco == Posicao.ESQUERDA) {
			return "(" + canibalEsquerda + "," + missionarioEsquerda + ",L,"
        			+ canibalDireita + "," + missionarioDireita + ")";
		} else {
			return "(" + canibalEsquerda + "," + missionarioEsquerda + ",R,"
        			+ canibalDireita + "," + missionarioDireita + ")";
		}
     }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Estado)) {
			return false;
		}
		Estado s = (Estado) obj;
        return (s.canibalEsquerda == canibalEsquerda && s.missionarioEsquerda == missionarioEsquerda
        		&& s.barco == barco && s.canibalDireita == canibalDireita
        		&& s.missionarioDireita == missionarioDireita);
	}
}
