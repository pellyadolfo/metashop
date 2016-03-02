package com.metashop.app.client.home;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.metashop.app.client.NameTokens;
import com.metashop.app.client.application.ApplicationPresenter;
import com.metashop.app.data.Category;
import com.metashop.app.dispatch.GetCategoriesRequest;
import com.metashop.app.dispatch.GetCategoriesResult;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers {
    @ProxyStandard
    @NameToken(NameTokens.HOME)
    public interface MyProxy extends ProxyPlace<HomePresenter> {
    }
    
    public interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
    	void setCategories(List<Category> categories);
    }
    
    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    public static final NestedSlot TYPE_CATEGORY = new NestedSlot();
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    // presenter is provided with all the information required to work
    // Inject marks the non default constructor
    @Inject
    public HomePresenter(final EventBus eventBus,
                          final MyView view,
                          final MyProxy proxy,
                          PlaceManager placeManager,		// This comes here because Proxy has been declared as place
                          DispatchAsync dispatcher) {		// Need to include dispatcher stuff in .gwt.xml
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
        
        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        
        getView().setUiHandlers(this);
        
        // preload categories
        loadCategories();
        loadBrands(null);
        loadFeatured(null);
        loadRecommended(null);
    	Logger rootLogger = Logger.getLogger("popo");
		rootLogger.log(Level.SEVERE, "request: ");
    }
    
    public void loadCategories() {
        dispatcher.execute(new GetCategoriesRequest("textToServer"), new AsyncCallback<GetCategoriesResult>() {
	        @Override
	        public void onFailure(Throwable caught) {
	            //getView().setServerResponse("An error occurred: " + caught.getMessage());
	        }
	
	        @Override
	        public void onSuccess(GetCategoriesResult result) {
	        	//getView().setCategories(getCategories());
	        	
	        	/* Create Root Logger */
	        	Logger rootLogger = Logger.getLogger("popo");
        		rootLogger.log(Level.SEVERE, "result: ");

	        	Iterator<Category> iterator = result.getCategories().iterator();
	        	while (iterator.hasNext()) {
	        		rootLogger.log(Level.SEVERE, "result: " + iterator.next().getName());
	        	}
	        	
	        	getView().setCategories(result.getCategories());
	        }
	    });
    }
    
    public void loadBrands(String name) {
        dispatcher.execute(new GetCategoriesRequest("textToServer"), new AsyncCallback<GetCategoriesResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetCategoriesResult result) {
            	//getView().setCategories(getCategories());
            }
        });
    }
    
    public void loadRecommended(String name) {
        dispatcher.execute(new GetCategoriesRequest("textToServer"), new AsyncCallback<GetCategoriesResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetCategoriesResult result) {
            	//getView().setCategories(getCategories());
            }
        });
    }
    
    @Override
    public void loadFeatured(String name) {
        dispatcher.execute(new GetCategoriesRequest("textToServer"), new AsyncCallback<GetCategoriesResult>() {
            @Override
            public void onFailure(Throwable caught) {
                //getView().setServerResponse("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(GetCategoriesResult result) {
            	//getView().setCategories(getCategories());
            }
        });
    }
}
