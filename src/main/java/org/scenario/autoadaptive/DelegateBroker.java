package org.scenario.autoadaptive;

import java.util.HashMap;
import java.util.Map;

public class DelegateBroker {
	
	private static Map<Integer,MetadataManager> dcToReplicaManagerMap = new HashMap<>();

	public Map<Integer,MetadataManager> getDcToReplicaManagerMap() {
		return dcToReplicaManagerMap;

	}  
	

}
