package org.scenario.autoadaptive;

import java.util.Collections;
import java.util.List;

import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.vms.Vm;
import org.scenario.Utils.Utils;
import org.scenario.cloudsimplus.AdaptedVm;

public class LoadBalancerWeightedLeastConnections implements LoadBalancer{

	
	/**
	 * This algorithm takes into consideration the number of current connections 
	 * each server/Vm has, and it introduces a "weight" component based on the 
	 * respective capacities of each server/Vm.
	 * Details about this algorithm and other load balancing algorithms 
	 * <a href="https://www.jscape.com/blog/load-balancing-algorithms"> here </a>
	 * 
	 * @author Lahiaouni Abderrahman
	 */

	/**
	 * Average lenght in terms of cpu utilization
	 * 
	 */
	public int avgCloudletLenghtInDc = 0;
	
	private Datacenter datacenter;

	@Override
	public Vm electVm(List<Vm> vmList) {
//		Vm candidate = vmList.get(Utils.givenUsingApache_whenGeneratingRandomIntegerBounded_thenCorrect(0, vmList.size()-1));
		Vm candidate = Collections.min(vmList, (vm1,vm2) -> ((AdaptedVm) vm1).getOrUpdateRequestCount(0) > ((AdaptedVm) vm2).getOrUpdateRequestCount(0) ? 1 : -1);
			for(Vm vm : vmList) {
				Utils.writeInAGivenFile("Log", String.valueOf(((AdaptedVm) vm).getOrUpdateRequestCount(0)) + " , " , true);
			}
			Utils.writeInAGivenFile("Log", " => " + String.valueOf(((AdaptedVm) candidate).getOrUpdateRequestCount(0)) + "\n" , true);
		// TODO here the number of connections to a vm
		// and to a dc should be ++
		// Also update avgCloudletLenght 
		return candidate;
	}
	
	public int updateAvgCloudletLenght() {
		List<Vm> vmList = this.datacenter.getVmList();
		
		for(Vm vm : this.datacenter.getVmList()) {
			avgCloudletLenghtInDc += ((AdaptedVm) vm).getOrUpdateAvgCloudletLenght(0);
		}
		avgCloudletLenghtInDc = Math.round((float)avgCloudletLenghtInDc / (float)vmList.size());
		return avgCloudletLenghtInDc;
		
	}

	@Override
	public void setDatacenter(Datacenter datacenter) {
		this.datacenter = datacenter;
		
	}
}
