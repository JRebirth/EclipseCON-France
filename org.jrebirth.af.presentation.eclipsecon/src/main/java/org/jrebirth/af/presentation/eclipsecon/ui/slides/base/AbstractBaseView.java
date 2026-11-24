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
package org.jrebirth.af.presentation.eclipsecon.ui.slides.base;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BlurType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javafx.scene.effect.Glow;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Bloom;
import javafx.animation.TranslateTransition;
import javafx.animation.Timeline;
import javafx.animation.ScaleTransition;
import javafx.animation.RotateTransition;
import javafx.animation.FadeTransition;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.image.RelImage;
import org.jrebirth.af.presentation.eclipsecon.resources.EcColors;
import org.jrebirth.af.presentation.eclipsecon.resources.EcFonts;
import org.jrebirth.af.presentation.eclipsecon.resources.EcImages;
import org.jrebirth.af.presentation.model.SlideContent;
import org.jrebirth.af.presentation.model.SlideItem;
import org.jrebirth.af.presentation.resources.PrezFonts;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;
import org.jrebirth.af.presentation.ui.base.SlideStep;

/**
 * The class <strong>AbstractTemplateView</strong>.
 * 
 * The view used to display a standard template slide.
 * 
 * @param <M> the generic type
 * @param <N> the number type
 * @param <C> the generic type
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseView<M extends AbstractBaseModel<?, ?, ?>, N extends Pane, C extends AbstractBaseController<?, ?>> extends
        AbstractSlideView<M, N, C> {

    /** Prefix used for css class. */
    private static final String ITEM_CLASS_PREFIX = "item";

    /** The sub title of this slide. */
    private Label secondaryTitle;

    /** The pane that hold the content. */
    private StackPane slideContentPane;

    /** The list of nodes taht reprensent each subslide. */
    private final List<Node> subSlides = new ArrayList<>();

    /** The current subslide node displayed. */
    private Node currentSubSlide;

    /** A lock managed by subslide. */
    private final boolean subSlideLock = false;

    /** The transitionn used between subslides. */
    private ParallelTransition subSlideTransition;

    /** The circle. */
    private Circle circle;

    /** The primary title. */
    private Label primaryTitle;

    /** The prez title. */
    // private Label prezTitle;

    /** The header rectangle. */
    private Rectangle headerRectangle;

    private ImageView headerPattern1;
    private ImageView headerPattern2;

    private ImageView eclipseCONLogo;
    private ImageView jrebirthLogo;

    private final Rectangle[] rectangles = new Rectangle[7];

    private final Color[] rectangleColors = {
            EcColors.RECTANGLE_1.get(),
            EcColors.RECTANGLE_2.get(),
            EcColors.RECTANGLE_3.get(),
            EcColors.RECTANGLE_4.get(),
            EcColors.RECTANGLE_5.get(),
            EcColors.RECTANGLE_6.get(),
            EcColors.RECTANGLE_7.get(),
    };

    // Page

    private ImageView page3Petals;
    private ImageView page1Petal;
    private ImageView pageRays;
    private ImageView pageSpark;
    /** The label that display the number of the page. */
    private Label pageLabel;

    private Group headerPatternGroup;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public AbstractBaseView(final M model) throws CoreException {
        super(model);
    }

    /**
     * Gets the sub title.
     * 
     * @return Returns the subTitle.
     */
    protected Label getSubTitle() {
        return this.secondaryTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().getStyleClass().add("Slide");

        this.slideContentPane = new StackPane();
        this.slideContentPane.setOpacity(1.0);
        this.slideContentPane.getStyleClass().add("content");

        // 85 151
        this.slideContentPane.setMinSize(854, 634);
        this.slideContentPane.setMaxSize(854, 634);
        this.slideContentPane.setPrefSize(854, 634);

        this.slideContentPane.setLayoutX(83.0);
        this.slideContentPane.setLayoutY(120.0);

        final Node header = getHeaderPanel();

        // initialize the begin properties for the transition
        this.slideContentPane.setScaleX(0);
        this.slideContentPane.setScaleY(0);

        if (!model().hasStep()) {
            addSubSlide(getContentPanel());
        }

        for (int i = 0; i < this.rectangles.length; i++) {
            final Rectangle r = new Rectangle(122, 11);
            r.setLayoutX(83.0 + i * 122);
            r.setLayoutY(120.0);
            r.setOpacity(0.0);
            r.setFill(this.rectangleColors[i]);

            this.rectangles[i] = r;
        }

        // final Node footer = getFooterPanel();

        header.setLayoutX(0);
        header.setLayoutY(0);

        buildLogo();
        buildPage();

        node().getChildren().addAll(this.eclipseCONLogo, this.jrebirthLogo, /* footer, */this.slideContentPane, header);
        node().getChildren().addAll(this.rectangles);

    }

    private void buildPage() {

        this.page3Petals = new ImageView();
        this.page3Petals.setLayoutX(900);
        this.page3Petals.setLayoutY(25);
        this.page3Petals.setTranslateX(200);
        this.page3Petals.setImage(EcImages.PAGE_3_PETALS.get());
        this.page3Petals.setEffect(new Glow(0.2));

        this.pageLabel = new Label(String.valueOf(model().getSlide().getPage()));
        this.pageLabel.setLayoutX(932);
        this.pageLabel.setLayoutY(42);
        this.pageLabel.setFont(PrezFonts.PAGE.get());
        this.pageLabel.setTextFill(this.rectangleColors[3]);
        this.pageLabel.setScaleX(0);
        this.pageLabel.setScaleY(0);

        this.page1Petal = new ImageView();
        this.page1Petal.setLayoutX(960);
        this.page1Petal.setLayoutY(16);
        this.page1Petal.setTranslateX(200);
        this.page1Petal.setTranslateY(-200);
        this.page1Petal.setImage(EcImages.PAGE_1_PETAL.get());
        this.page1Petal.setEffect(new Glow(0.2));

        this.pageRays = new ImageView();
        this.pageRays.setLayoutX(890);
        this.pageRays.setLayoutY(30);
        this.pageRays.setOpacity(0);
        this.pageRays.setEffect(new Bloom(0.7));
        this.pageRays.setImage(EcImages.PAGE_RAYS.get());

        this.pageSpark = new ImageView();
        this.pageSpark.setLayoutX(960);
        this.pageSpark.setLayoutY(8);
        this.pageSpark.setImage(EcImages.PAGE_SPARK.get());
        this.pageSpark.setEffect(new Bloom(1.0));
        this.pageSpark.setOpacity(0);
        this.pageSpark.setScaleX(0);
        this.pageSpark.setScaleY(0);

        node().getChildren().addAll(this.page3Petals, this.pageLabel, this.page1Petal, this.pageRays, this.pageSpark);

    }

    /**
     * TODO To complete.
     */
    private void buildLogo() {
        this.jrebirthLogo = new ImageView(EcImages.JREBIRTH_LOGO.get());
        this.jrebirthLogo.setLayoutX(30);
        this.jrebirthLogo.setLayoutY(1030);

        this.eclipseCONLogo = new ImageView(EcImages.ECLIPSECON_LOGO.get());
        this.eclipseCONLogo.setLayoutX(943.0);
        this.eclipseCONLogo.setLayoutY(1030);
    }

    /**
     * Show en aempty slide.
     */
    protected void showEmptySlide() {
        this.subSlides.add(model().getStepPosition(), null);
    }

    /**
     * Add a subslide node.
     * 
     * @param defaultSubSlide the subslide node
     */
    private void addSubSlide(final Node defaultSubSlide) {

        this.subSlides.add(model().getStepPosition(), defaultSubSlide);
        this.slideContentPane.getChildren().add(defaultSubSlide);

        StackPane.setAlignment(defaultSubSlide, Pos.CENTER);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        reload();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {

        this.primaryTitle.setOpacity(0.0);
        this.secondaryTitle.setOpacity(0.0);

        this.page3Petals.setTranslateX(200);

        this.pageLabel.setScaleX(0);
        this.pageLabel.setScaleY(0);

        this.page1Petal.setTranslateX(200);
        this.page1Petal.setTranslateY(-200);

        this.pageRays.setOpacity(0.0);

        this.pageSpark.setScaleX(0);
        this.pageSpark.setScaleY(0);
        this.pageSpark.setOpacity(0);

        this.headerRectangle.setTranslateX(-1100);
        this.headerPatternGroup.setTranslateY(-150);

        this.eclipseCONLogo.setTranslateY(500);
        this.jrebirthLogo.setTranslateY(500);

        for (final Rectangle r : this.rectangles) {
            r.setOpacity(0.0);
        }

        this.slideContentPane.setScaleX(0);
        this.slideContentPane.setScaleY(0);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void reload() {

        final FadeTransition primaryFade = new FadeTransition(Duration.millis(600), this.primaryTitle);
        primaryFade.setDelay(Duration.millis(1000));
        primaryFade.setFromValue(0);
        primaryFade.setToValue(1.0);

        final FadeTransition secondaryFade = new FadeTransition(Duration.millis(600), this.secondaryTitle);
        secondaryFade.setDelay(Duration.millis(1200));
        secondaryFade.setFromValue(0);
        secondaryFade.setToValue(1.0);

        final TranslateTransition eclipseLogoUp = new TranslateTransition(Duration.millis(300), this.eclipseCONLogo);
        eclipseLogoUp.setDelay(Duration.millis(1400));
        eclipseLogoUp.setToY(-500);

        final TranslateTransition jrebirthLogoUp = new TranslateTransition(Duration.millis(300), this.jrebirthLogo);
        jrebirthLogoUp.setDelay(Duration.millis(1700));
        jrebirthLogoUp.setToY(-500);

        final ParallelTransition reloadAnimation = new ParallelTransition();
        reloadAnimation.getChildren().addAll(
                buildContentAnimation(),
                buildHeaderAnimation(),
                buildPageAnimation(),
                primaryFade,
                secondaryFade,
                eclipseLogoUp,
                jrebirthLogoUp);
        reloadAnimation.play();
    }

    private Animation buildHeaderAnimation() {
        final TranslateTransition headerSlide = new TranslateTransition(Duration.millis(500), this.headerRectangle);
        headerSlide.setByX(1100);
        headerSlide.setInterpolator(Interpolator.EASE_IN);

        final TranslateTransition patternDrop = new TranslateTransition(Duration.millis(500), this.headerPatternGroup);
        patternDrop.setByY(150);
        patternDrop.setInterpolator(Interpolator.EASE_IN);

        final ParallelTransition headerAnimation = new ParallelTransition(headerSlide, patternDrop);
        headerAnimation.setDelay(Duration.millis(200));
        return headerAnimation;
    }

    private Animation buildPageAnimation() {
        final RotateTransition petalRotate = new RotateTransition(Duration.millis(500), this.page3Petals);
        petalRotate.setByAngle(1080);
        petalRotate.setInterpolator(Interpolator.EASE_IN);

        final TranslateTransition petalShift = new TranslateTransition(Duration.millis(400), this.page3Petals);
        petalShift.setByX(-200);
        petalShift.setInterpolator(Interpolator.EASE_IN);

        final ParallelTransition step1 = new ParallelTransition(petalRotate, petalShift);
        step1.setNode(this.page3Petals);

        final ScaleTransition pageZoom = new ScaleTransition(Duration.millis(400), this.pageLabel);
        pageZoom.setFromX(0);
        pageZoom.setFromY(0);
        pageZoom.setToX(1.0);
        pageZoom.setToY(1.0);

        final TranslateTransition monoPetal = new TranslateTransition(Duration.millis(300), this.page1Petal);
        monoPetal.setByX(-200);
        monoPetal.setByY(200);
        monoPetal.setInterpolator(Interpolator.EASE_IN);

        final ParallelTransition step2 = new ParallelTransition(pageZoom, monoPetal);

        final FadeTransition raysFade = new FadeTransition(Duration.millis(300), this.pageRays);
        raysFade.setFromValue(0.0);
        raysFade.setToValue(1.0);

        final FadeTransition sparkFade = new FadeTransition(Duration.millis(300), this.pageSpark);
        sparkFade.setFromValue(0.0);
        sparkFade.setToValue(1.0);

        final ScaleTransition sparkZoom = new ScaleTransition(Duration.millis(200), this.pageSpark);
        sparkZoom.setFromX(0);
        sparkZoom.setFromY(0);
        sparkZoom.setToX(1.0);
        sparkZoom.setToY(1.0);

        final ParallelTransition step4 = new ParallelTransition(sparkFade, sparkZoom);
        step4.setNode(this.pageSpark);

        final SequentialTransition pageAnimation = new SequentialTransition(step1, step2, raysFade, step4);
        pageAnimation.setDelay(Duration.millis(400));
        return pageAnimation;
    }

    private SequentialTransition buildContentAnimation() {
        final TranslateTransition translate = new TranslateTransition(Duration.millis(600), this.slideContentPane);
        translate.setFromX(1024 / 2);
        translate.setFromY(768 / 2);
        translate.setToX(0);
        translate.setToY(0);

        final ScaleTransition scale = new ScaleTransition(Duration.millis(600), this.slideContentPane);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(1);
        scale.setToY(1);

        final ParallelTransition slideIn = new ParallelTransition(translate, scale);
        slideIn.setDelay(Duration.millis(100));
        slideIn.setNode(this.slideContentPane);

        return new SequentialTransition(slideIn, displayRectangle());
    }

    private Animation displayRectangle() {
        final SequentialTransition rectAnimation = new SequentialTransition();
        for (final Rectangle r : this.rectangles) {
            final FadeTransition fade = new FadeTransition(Duration.millis(200), r);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            rectAnimation.getChildren().add(fade);
        }
        return rectAnimation;
    }

    protected void bindNode(final Node node) {
        node.scaleXProperty().bind(bindWidth());
        node.scaleYProperty().bind(bindHeight());
    }

    protected NumberBinding bindHeight() {
        return Bindings.divide(model().localFacade().globalFacade().application().stage().heightProperty(), 768);
    }

    protected NumberBinding bindWidth() {
        return Bindings.divide(model().localFacade().globalFacade().application().stage().widthProperty(), 1024);
    }

    protected VBox buildDefaultContent(final SlideContent slideContent) {

        if (slideContent.getTitle() != null && this.secondaryTitle != null) {
            this.secondaryTitle.setText(slideContent.getTitle());
        }

        final VBox vbox = new VBox();

        if (model().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(model().getSlide().getStyle());
        }

        if (slideContent != null) {
            for (final SlideItem item : slideContent.getItem()) {
                addSlideItem(vbox, item);
            }
        }

        return vbox;
    }

    protected void addSlideItem(final VBox vbox, final SlideItem item) {

        Node node = null;
        if (item.isLink()) {

            final Hyperlink link = new Hyperlink();
            link.setOpacity(1.0);
            link.setText(item.getValue());

            link.getStyleClass().add("link" + item.getLevel());

            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    final ClipboardContent content = new ClipboardContent();
                    content.putString("http://" + ((Hyperlink) e.getSource()).getText());
                    Clipboard.getSystemClipboard().setContent(content);
                }
            });
            node = link;

        } else if (item.isHtml()) {

            final WebView web = new WebView();
            web.setFontScale(1.4);
            web.getEngine().loadContent(item.getValue());

            VBox.setVgrow(web, Priority.NEVER);

            node = web;

        } else if (item.getImage() != null) {

            final Image image = Resources.create(new RelImage(item.getImage())).module(getClass().getModule()).get();
            final ImageView imageViewer = new ImageView();
            imageViewer.getStyleClass().add(ITEM_CLASS_PREFIX + item.getLevel());
            imageViewer.setImage(image);

            node = imageViewer;
        } else {

            final Text text = new Text();
            text.getStyleClass().add(ITEM_CLASS_PREFIX + item.getLevel());
            text.setText(item.getValue() == null ? "" : item.getValue());

            node = text;
        }

        if (item.getStyle() != null) {
            node.getStyleClass().add(item.getStyle());
        }

        if (item.getScale() != 1.0) {
            node.setScaleX(item.getScale());
            node.setScaleY(item.getScale());
        }

        vbox.getChildren().add(node);
    }

    public void showSlideStep(final SlideStep slideStep) {

        if (this.subSlides.size() >= model().getStepPosition() || this.subSlides.get(model().getStepPosition()) == null) {
            addSubSlide(buildDefaultContent(model().getContent(slideStep)));
        }
        final Node nextSlide = this.subSlides.get(model().getStepPosition());

        if (this.currentSubSlide != null && nextSlide != null) {
            performStepAnimation(nextSlide);
        } else {
            this.currentSubSlide = nextSlide;
        }

    }

    protected void showCustomSlideStep(final Node node) {

        addSubSlide(node);
        final Node nextSlide = this.subSlides.get(model().getStepPosition());
        if (this.currentSubSlide != null && nextSlide != null) {

            performStepAnimation(nextSlide);
        } else {
            this.currentSubSlide = nextSlide;
        }
    }

    private void performStepAnimation(final Node nextSlide) {

        this.subSlideTransition = new ParallelTransition();
        this.subSlideTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                AbstractBaseView.this.currentSubSlide = nextSlide;
            }
        });

        final SequentialTransition hideCurrent = new SequentialTransition();
        hideCurrent.setNode(this.currentSubSlide);

        final TranslateTransition slideOut = new TranslateTransition(Duration.millis(400), this.currentSubSlide);
        slideOut.setFromY(0);
        slideOut.setToY(-700);

        final Timeline hideTimeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(this.currentSubSlide.visibleProperty(), true)),
                new KeyFrame(Duration.millis(1), new KeyValue(this.currentSubSlide.visibleProperty(), false)));
        hideCurrent.getChildren().addAll(slideOut, hideTimeline);

        final SequentialTransition showNext = new SequentialTransition();
        showNext.setNode(nextSlide);

        final Timeline showTimeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(nextSlide.visibleProperty(), false)),
                new KeyFrame(Duration.millis(1), new KeyValue(nextSlide.visibleProperty(), true)));

        final TranslateTransition slideIn = new TranslateTransition(Duration.millis(400), nextSlide);
        slideIn.setFromY(700);
        slideIn.setToY(0);

        showNext.getChildren().addAll(showTimeline, slideIn);

        this.subSlideTransition.getChildren().addAll(hideCurrent, showNext);
        this.subSlideTransition.play();

    }

    protected StackPane getSlideContent() {
        return this.slideContentPane;
    }

    protected Node getHeaderPanel() {

        final Pane headerPane = new Pane();
        headerPane.getStyleClass().add("header");
        headerPane.setLayoutX(0.0);
        headerPane.setLayoutY(0.0);
        headerPane.setMinWidth(1024);
        headerPane.setPrefWidth(1024);

        final DropShadow primaryGlow = new DropShadow();
        primaryGlow.setBlurType(BlurType.TWO_PASS_BOX);
        primaryGlow.setColor(EcColors.SLIDE_TITLE_GLOW.get());
        primaryGlow.setRadius(8);
        primaryGlow.setSpread(0.5);
        final Bloom primaryBloom = new Bloom(1.0);
        primaryBloom.setInput(primaryGlow);

        this.primaryTitle = new Label();
        this.primaryTitle.setFont(EcFonts.SLIDE_TITLE.get());
        this.primaryTitle.setTextFill(EcColors.SLIDE_TITLE.get());
        this.primaryTitle.setText(model().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"));
        this.primaryTitle.setLayoutX(100);
        this.primaryTitle.setLayoutY(16);
        this.primaryTitle.setOpacity(0);
        this.primaryTitle.setEffect(primaryBloom);

        final DropShadow secondaryGlow = new DropShadow();
        secondaryGlow.setBlurType(BlurType.TWO_PASS_BOX);
        secondaryGlow.setColor(EcColors.SLIDE_TITLE_GLOW.get());
        secondaryGlow.setRadius(4);
        secondaryGlow.setSpread(0.6);
        final Bloom secondaryBloom = new Bloom(1.0);
        secondaryBloom.setInput(secondaryGlow);

        this.secondaryTitle = new Label();
        this.secondaryTitle.setFont(EcFonts.SLIDE_SUB_TITLE.get());
        this.secondaryTitle.setTextFill(EcColors.SLIDE_TITLE.get());
        this.secondaryTitle.setOpacity(0);
        this.secondaryTitle.setEffect(secondaryBloom);
        this.secondaryTitle.setLayoutX(100);
        this.secondaryTitle.setLayoutY(70);
        this.secondaryTitle.setMinWidth(450);
        this.secondaryTitle.setAlignment(Pos.CENTER_LEFT);
        this.secondaryTitle.setTextAlignment(TextAlignment.RIGHT);

        this.headerRectangle = new Rectangle();
        this.headerRectangle.setLayoutX(0);
        this.headerRectangle.setLayoutY(0);
        this.headerRectangle.setWidth(860);
        this.headerRectangle.setHeight(115.0);
        this.headerRectangle.setTranslateX(-1100);
        this.headerRectangle.setFill(Color.BLACK);

        this.headerPattern1 = new ImageView(EcImages.HEADER_PATTERN.get());
        this.headerPattern1.setBlendMode(BlendMode.SRC_OVER);
        this.headerPattern1.setOpacity(0.4);
        this.headerPattern1.setLayoutX(-164);
        this.headerPattern1.setLayoutY(0);

        this.headerPattern2 = new ImageView(EcImages.HEADER_PATTERN.get());
        this.headerPattern2.setBlendMode(BlendMode.SRC_OVER);
        this.headerPattern2.setOpacity(0.2);
        this.headerPattern2.setLayoutX(861);
        this.headerPattern2.setLayoutY(0);

        this.headerPatternGroup = new Group();
        this.headerPatternGroup.setTranslateY(-150);
        this.headerPatternGroup.getChildren().addAll(this.headerPattern1, this.headerPattern2);

        headerPane.getChildren().addAll(this.headerRectangle, this.headerPatternGroup, this.primaryTitle, this.secondaryTitle);

        return headerPane;

    }    protected Node getContentPanel() {
        return buildDefaultContent(model().getDefaultContent());
    }

    /**
     * Build and return the footer panel.
     * 
     * @return the footer panel
     */
    protected Node getFooterPanel() {
        final Label _b6 = new Label(String.valueOf(model().getSlide().getPage()));
        _b6.setFont(PrezFonts.PAGE.get());
        this.pageLabel = _b6;

        final AnchorPane ap = new AnchorPane();
        ap.getChildren().addAll(this.pageLabel);;
        AnchorPane.setRightAnchor(this.pageLabel, 20.0);

        final StackPane sp = new StackPane();
        sp.getStyleClass().add("footer");
        sp.setPrefHeight(35.0);
        sp.setMinHeight(Region.USE_PREF_SIZE);
        sp.setMaxHeight(Region.USE_PREF_SIZE);
        sp.getChildren().addAll(ap);;

        StackPane.setAlignment(ap, Pos.CENTER_RIGHT);

        return sp;
    }
}
