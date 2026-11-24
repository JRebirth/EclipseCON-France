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
package org.jrebirth.af.presentation.eclipsecon.ui.slides.animation;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.shape.SVGPath;
import javafx.animation.TranslateTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.RotateTransition;
import javafx.animation.PauseTransition;
import javafx.animation.PathTransition;
import javafx.animation.FillTransition;
import javafx.animation.FadeTransition;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.presentation.eclipsecon.resources.EcColors;
import org.jrebirth.af.presentation.eclipsecon.ui.slides.base.AbstractBaseView;

/**
 * 
 * The class <strong>PatternView</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class AnimationView extends AbstractBaseView<AnimationModel, Pane, AnimationController> {

    /** The demo rectangle. */
    private Rectangle demoRectangle;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public AnimationView(final AnimationModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getContentPanel() {
        return buildDefaultContent(model().getDefaultContent());
    }

    /**
     * .
     * 
     * @param slideStep the slide step
     */
    public void buildDemoRectangle(final AnimationSlideStep slideStep) {

        final VBox vb = buildDefaultContent(model().getContent(slideStep));

        final Rectangle _b3 = new Rectangle();
        _b3.setWidth(200);
        _b3.setHeight(200);
        _b3.setStrokeWidth(10);
        _b3.setFill(EcColors.GRADIENT_3.get());
        this.demoRectangle = _b3;
        vb.getChildren().add(0, this.demoRectangle);
        StackPane.setAlignment(this.demoRectangle, Pos.TOP_CENTER);

        showCustomSlideStep(vb);
    }

    /**
     * TODO To complete.
     */
    public void showFade() {

        final FadeTransition _b4 = new FadeTransition();
        _b4.setNode(this.demoRectangle);
        _b4.setFromValue(0.0);
        _b4.setToValue(1.0);
        _b4.setAutoReverse(true);
        _b4.setCycleCount(Animation.INDEFINITE);
        _b4.setInterpolator(Interpolator.EASE_IN);
        _b4.setDuration(Duration.seconds(1));
        _b4.play();
    }

    /**
     * TODO To complete.
     */
    public void showFill() {
        final FillTransition _b7 = new FillTransition();
        _b7.setShape(this.demoRectangle);
        _b7.setFromValue(EcColors.GRADIENT_1.get());
        _b7.setToValue(EcColors.GRADIENT_2.get());
        _b7.setAutoReverse(true);
        _b7.setCycleCount(Animation.INDEFINITE);
        _b7.setInterpolator(Interpolator.EASE_IN);
        _b7.setDuration(Duration.seconds(1));
        _b7.play();

    }

    /**
     * TODO To complete.
     */
    public void showPath() {
        final String path = "M0 230.717C0 230.717 206.085 48.7446 313.587 86.8569 421.089 124.969 407.816 319.824 506.692 298.352 605.567 276.881 704.442 -39.8258 800 4.19133";
        final SVGPath svgPath = new SVGPath();
        svgPath.setContent(path);
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(this.demoRectangle);
        pathTransition.setPath(svgPath);
        pathTransition.setAutoReverse(true);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.EASE_IN);
        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.play();

    }

    /**
     * TODO To complete.
     */
    public void showPause() {

        final RotateTransition rotate = new RotateTransition();
        rotate.setNode(this.demoRectangle);
        rotate.setFromAngle(0);
        rotate.setToAngle(180);
        rotate.setInterpolator(Interpolator.EASE_IN);
        rotate.setDuration(Duration.millis(400));

        final PauseTransition pause = new PauseTransition();
        pause.setDuration(Duration.millis(600));

        final SequentialTransition sequential = new SequentialTransition();
        sequential.setAutoReverse(true);
        sequential.setCycleCount(Animation.INDEFINITE);
        sequential.getChildren().addAll(rotate, pause);
        sequential.play();
    }

    /**
     * TODO To complete.
     */
    public void showRotate() {
        final RotateTransition _b5 = new RotateTransition();
        _b5.setNode(this.demoRectangle);
        _b5.setAutoReverse(true);
        _b5.setFromAngle(0);
        _b5.setToAngle(540);
        _b5.setCycleCount(Animation.INDEFINITE);
        _b5.setInterpolator(Interpolator.EASE_IN);
        _b5.setDuration(Duration.seconds(1));
        _b5.play();

    }

    /**
     * TODO To complete.
     */
    public void showScale() {
        final ScaleTransition _b10 = new ScaleTransition();
        _b10.setNode(this.demoRectangle);
        _b10.setFromX(1.0);
        _b10.setByX(0.1);
        _b10.setToX(2.0);
        _b10.setFromY(1.0);
        _b10.setByY(0.1);
        _b10.setToY(2.0);
        _b10.setAutoReverse(true);
        _b10.setCycleCount(Animation.INDEFINITE);
        _b10.setInterpolator(Interpolator.EASE_IN);
        _b10.setDuration(Duration.seconds(1));
        _b10.play();

    }

    /**
     * TODO To complete.
     */
    public void showStroke() {
        final StrokeTransition _b8 = new StrokeTransition();
        _b8.setShape(this.demoRectangle);
        _b8.setFromValue(EcColors.GRADIENT_1.get());
        _b8.setToValue(EcColors.GRADIENT_2.get());
        _b8.setAutoReverse(true);
        _b8.setCycleCount(Animation.INDEFINITE);
        _b8.setInterpolator(Interpolator.EASE_IN);
        _b8.setDuration(Duration.seconds(1));
        _b8.play();
    }

    /**
     * TODO To complete.
     */
    public void showTranslate() {

        final TranslateTransition _b9 = new TranslateTransition();
        _b9.setNode(this.demoRectangle);
        _b9.setFromX(0.0);
        _b9.setToX(400.0);
        _b9.setFromY(0.0);
        _b9.setToY(200);
        _b9.setAutoReverse(true);
        _b9.setCycleCount(Animation.INDEFINITE);
        _b9.setInterpolator(Interpolator.EASE_BOTH);
        _b9.setDuration(Duration.seconds(1));
        _b9.play();
    }

}
