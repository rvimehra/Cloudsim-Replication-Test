package org.replicationTest.replication;

import org.cloudbus.cloudsim.resources.File;
import org.replicationTest.cloudsim.ObjectFile;

public class Replica extends ObjectFile implements IReplica{

	
	public Replica(File file) throws IllegalArgumentException {
		super(file);
		// TODO Auto-generated constructor stub
	}
	
	public Replica(String fileName, int fileSize) {
		super(fileName, fileSize);
		// TODO Auto-generated constructor stub
	}

}