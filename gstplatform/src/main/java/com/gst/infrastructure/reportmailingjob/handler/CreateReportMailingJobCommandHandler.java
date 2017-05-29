/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.gst.infrastructure.reportmailingjob.handler;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.reportmailingjob.ReportMailingJobConstants;
import com.gst.infrastructure.reportmailingjob.service.ReportMailingJobWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = ReportMailingJobConstants.REPORT_MAILING_JOB_ENTITY_NAME, action = "CREATE")
public class CreateReportMailingJobCommandHandler implements NewCommandSourceHandler {
    private final ReportMailingJobWritePlatformService reportMailingJobWritePlatformService;
    
    @Autowired
    public CreateReportMailingJobCommandHandler(final ReportMailingJobWritePlatformService reportMailingJobWritePlatformService) {
        this.reportMailingJobWritePlatformService = reportMailingJobWritePlatformService;
    }

    @Override
    @Transactional
    public CommandProcessingResult processCommand(JsonCommand jsonCommand) {
        return this.reportMailingJobWritePlatformService.createReportMailingJob(jsonCommand);
    }
}