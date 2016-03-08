/*
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.metashop.app.middleware;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.metashop.app.data.Brand;
import com.metashop.app.data.Product;
import com.metashop.app.dispatch.GetBrandsRequest;
import com.metashop.app.dispatch.GetBrandsResult;
import com.metashop.app.dispatch.GetDetailsRequest;
import com.metashop.app.dispatch.GetDetailsResult;

public class GetDetailsHandler extends AHandler implements ActionHandler<GetDetailsRequest, GetDetailsResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    GetDetailsHandler(ServletContext servletContext, Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public GetDetailsResult execute(GetDetailsRequest action, ExecutionContext context) throws ActionException {
        // fetch brands
        Product product = getDao().getDetails(action);
        GetDetailsResult result = new GetDetailsResult(product);

        return result;
    }

    @Override
    public Class<GetDetailsRequest> getActionType() {
        return GetDetailsRequest.class;
    }

	@Override
	public void undo(GetDetailsRequest action, GetDetailsResult result, ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}
}