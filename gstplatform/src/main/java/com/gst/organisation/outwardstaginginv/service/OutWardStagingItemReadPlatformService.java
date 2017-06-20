package com.gst.organisation.outwardstaginginv.service;

import java.util.List;

import com.gst.organisation.outwardstaginginv.data.OutWardStagingItemData;

/**
 * @author Trigital
 * 
 */
public interface OutWardStagingItemReadPlatformService {

	OutWardStagingItemData retrieveOutWardItemData(Long id);

	List<OutWardStagingItemData> retriveOutwardStagingInvItems(Long invoiceId);

}
