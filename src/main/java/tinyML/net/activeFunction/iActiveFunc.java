package tinyML.net.activeFunction;

import tinyML.dataType.Vector;

public interface iActiveFunc {

	//
	public Vector active(Vector sum);

	//
	public Vector derived(Vector sum);
}
