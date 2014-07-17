package controller.validator.error;


import graph.comp.Flow;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;

import controller.validator.Validator;
import controller.validator.ValidatorErrorManager;


/**gestisce i controlli e la trasmissione di eventuali messaggi d'errore che
 * sono coinvolti nell'accertare che un certo grafo abbia o meno al suo interno delle
 * ciclicit�. Un grafo si dice ciclico se esiste almeno un nodo tale per cui nella
 * lista dei suoi predecessori (locali e globali) � presente il nodo stesso.
 * In termini grafici una ciclicit� � una situazione del genere:
 * <p><img src="LoopDetectedInGraphError.png"/> 
 * <p> questo controllo va eseguito quando:
 * <ul>
 *  <li>si crea un nuovo flusso: il nuovo flusso pu� creare un loop all'interno del grafo</li>
 * </ul>
 * 
 * @author Koldar
 * @version 1.1
 * @see Validator
 * @see ValidatorErrorManager
 * @see LoopDetectedInGraphException
 *
 */
public class LoopDetectedInGraphErrorManager extends ValidatorErrorManager<LoopDetectedInGraphException>{

	public static final int IDERROR=3;
	
	public LoopDetectedInGraphErrorManager(){
		super(IDERROR,LoopDetectedInGraphException.class);
	}
	/**indica le azioni che devono essere eseguite per rilevare eventuali ciclicit�
	 * all'interno del grafo 
	 * 
	 * @param _source indica il nodo che si intende inserire che pu� generare una ciclicit�
	 * @param _target non usato; pu� essere impostato su qualunque valore
	 * @throws LoopDetectedInGraphException quando il grafo passato contiene almeno una ciclicit�
	 */
	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _copiedgraph, boolean _islastnode) throws LoopDetectedInGraphException {
		//alternative source for reveal errors
		/*if (new CycleDetector<Node, Flow>(_copiedgraph).detectCycles()){
			throw new LoopDetectedInGraphException(this.getErrorMessage());
		}*/
		if (new CycleDetector<Node,Flow>(_copiedgraph).detectCyclesContainingVertex(_source)){
			throw new LoopDetectedInGraphException();
		}
	}

}
