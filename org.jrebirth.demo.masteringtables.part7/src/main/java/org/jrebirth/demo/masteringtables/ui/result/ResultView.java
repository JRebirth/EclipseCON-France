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
package org.jrebirth.demo.masteringtables.ui.result;

import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.SVGPathBuilder;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;
import org.jrebirth.core.ui.annotation.RootNodeId;
import org.jrebirth.demo.masteringtables.resources.MTColors;
import org.jrebirth.demo.masteringtables.resources.MTImages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ResultView.
 */
@RootNodeId("ResultPanel")
public class ResultView extends DefaultView<ResultModel, BorderPane, ResultController> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultView.class);

    private Label ratioLabel;
    private Label timeLabel;
    private Label successLabel;
    private Label failureLabel;

    private ImageView monsterImage;

    private SVGPath timeBean;

    private SVGPath successBean;

    private SVGPath failureBean;

    private Circle ratioCircle;

    private ImageView successIcon;

    private ImageView failureIcon;

    /**
     * Instantiates a new page view.
     * 
     * @param model the model
     * @throws CoreException the core exception
     */
    public ResultView(final ResultModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        getRootNode().setFocusTraversable(true);

        final String beanPath = "M264.489,353.896h0.152c-10.026-28-15.393-58.608-15.192-90.053c0.21-33.038,6.578-64.879,17.979-93.896"
                + "c-0.077,0.192-0.156,0.299-0.229,0.491c3.331-11.38,5.158-23.444,5.236-35.892C272.895,62.568,214.922,3.827,142.943,3.364"
                + "c-71.979-0.46-130.7,57.504-131.16,129.482c-0.081,12.446,1.591,24.485,4.778,35.907c-0.073-0.192-0.15-0.387-0.224-0.578"
                + "c11.027,29.158,16.985,61.041,16.776,94.078c-0.203,31.442-5.963,61.643-16.347,89.643h0.15c-4.319,13-6.706,26.28-6.799,40.542"
                + "C9.66,464.417,67.636,523.015,139.613,523.474c71.979,0.459,130.693-57.118,131.155-129.096"
                + "C270.861,380.109,268.652,365.896,264.489,353.896z";

        this.timeBean = SVGPathBuilder.create()
                .content(beanPath)
                .scaleX(1.0)
                .scaleY(1.0)
                .layoutX(80)
                .layoutY(14)
                .rotate(-34.0)
                .fill(Color.LIGHTGREY)
                .effect(getDropShadow())
                .build();

        this.successBean = SVGPathBuilder.create()
                .content(beanPath)
                .scaleX(0.84)
                .scaleY(0.84)
                .layoutX(270)
                .layoutY(80)
                .rotate(62.0)
                .fill(Color.LIGHTGREY)
                .effect(getDropShadow())
                .build();

        this.failureBean = SVGPathBuilder.create()
                .content(beanPath)
                .scaleX(0.73)
                .scaleY(0.73)
                .layoutX(460)
                .layoutY(85)
                .rotate(-58)
                .fill(Color.LIGHTGREY)
                .effect(getDropShadow())
                .build();

        this.ratioCircle = CircleBuilder.create()
                .fill(Color.LIGHTGREY)
                .radius(150)
                .layoutX(154)
                .layoutY(154)
                .effect(getDropShadow())
                .build();

        // Image

        this.successIcon = ImageViewBuilder.create()
                .image(MTImages.RESULT_SUCCESS_ICON.get())
                .layoutX(495)
                .layoutY(195)
                .opacity(0)
                .build();

        this.failureIcon = ImageViewBuilder.create()
                .image(MTImages.RESULT_FAILURE_ICON.get())
                .layoutX(666)
                .layoutY(320)
                .opacity(0)
                .build();

        this.monsterImage = ImageViewBuilder.create()
                .image(MTImages.RESULT_MONSTER.get())
                .scaleX(0.9)
                .scaleY(0.9)
                .layoutX(410)
                .layoutY(1200)
                .build();

        this.ratioLabel = LabelBuilder.create()
                .id("ResultRatioLabel")
                .layoutX(28)
                .layoutY(80)
                .scaleX(0)
                .scaleY(0)
                .build();

        this.timeLabel = LabelBuilder.create()
                .id("ResultTimeLabel")
                .layoutX(170)
                .layoutY(330)
                .scaleX(0)
                .scaleY(0)
                .build();

        this.successLabel = LabelBuilder.create()
                .id("ResultSuccessLabel")
                .layoutX(444)
                .layoutY(255)
                .scaleX(0)
                .scaleY(0)
                .build();

        this.failureLabel = LabelBuilder.create()
                .id("ResultFailureLabel")
                .layoutX(645)
                .layoutY(390)
                .scaleX(0)
                .scaleY(0)
                .build();

        final Pane p = PaneBuilder
                .create()
                .prefHeight(600)
                .prefWidth(800)
                .minHeight(600)
                .minWidth(800)
                .children(this.failureBean, this.successBean, this.timeBean, this.ratioCircle, this.monsterImage, this.successIcon, this.failureIcon, this.timeLabel, this.successLabel,
                        this.failureLabel, this.ratioLabel)
                .build();

        getRootNode().setCenter(p);

    }

    private Effect getDropShadow() {
        return DropShadowBuilder.create()
                .offsetX(0)
                .offsetY(5)
                .color(MTColors.BEAN_SHADOW.get())
                .blurType(BlurType.GAUSSIAN)
                .radius(5)
                .spread(0.1)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        // Animate this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {

        // Reset the animation
        this.ratioCircle.setFill(Color.LIGHTGRAY);
        this.timeBean.setFill(Color.LIGHTGRAY);
        this.successBean.setFill(Color.LIGHTGRAY);
        this.failureBean.setFill(Color.LIGHTGRAY);

        this.ratioLabel.setScaleX(0);
        this.timeLabel.setScaleX(0);
        this.successLabel.setScaleX(0);
        this.failureLabel.setScaleX(0);

        this.ratioLabel.setScaleY(0);
        this.timeLabel.setScaleY(0);
        this.successLabel.setScaleY(0);
        this.failureLabel.setScaleY(0);

        this.monsterImage.setTranslateY(0);

        this.failureIcon.setOpacity(0);
        this.successIcon.setOpacity(0);
    }

    /**
     * @return Returns the timeLabel.
     */
    Label getTimeLabel() {
        return this.timeLabel;
    }

    /**
     * @return Returns the successLabel.
     */
    Label getSuccessLabel() {
        return this.successLabel;
    }

    /**
     * @return Returns the failureLabel.
     */
    Label getFailureLabel() {
        return this.failureLabel;
    }

    /**
     * @return Returns the ratioLabel.
     */
    Label getRatioLabel() {
        return this.ratioLabel;
    }

}