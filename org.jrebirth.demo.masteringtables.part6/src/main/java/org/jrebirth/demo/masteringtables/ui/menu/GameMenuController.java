/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.demo.masteringtables.ui.menu;

import javafx.event.ActionEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.demo.masteringtables.beans.Page;
import org.jrebirth.demo.masteringtables.ui.MTWaves;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GameMenuController.
 */
public class GameMenuController extends DefaultController<GameMenuModel, GameMenuView> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GameMenuController.class);

    /**
     * Instantiates a new start controller.
     * 
     * @param view the view
     * @throws CoreException the core exception
     */
    public GameMenuController(final GameMenuView view) throws CoreException {
        super(view);
    }

    /**
     * When Play button is pressed.
     * 
     * @param actionEvent the action event
     */
    public void onActionPlay(final ActionEvent actionEvent) {
        // When play button is trigered launch the game
        getModel().sendWave(MTWaves.SHOW_PAGE, WaveData.build(MTWaves.PAGE, Page.Game));
    }

}