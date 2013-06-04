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
package org.jrebirth.presentation.eclipsecon.ui.slides.base;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.HyperlinkBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BloomBuilder;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.GlowBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.scene.web.WebView;
import javafx.scene.web.WebViewBuilder;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.PrezFonts;
import org.jrebirth.presentation.eclipsecon.resources.EcColors;
import org.jrebirth.presentation.eclipsecon.resources.EcFonts;
import org.jrebirth.presentation.eclipsecon.resources.EcImages;
import org.jrebirth.presentation.model.SlideContent;
import org.jrebirth.presentation.model.SlideItem;
import org.jrebirth.presentation.ui.base.AbstractSlideView;
import org.jrebirth.presentation.ui.base.SlideStep;

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

        if (!getModel().hasStep()) {
            addSubSlide(getContentPanel());
        }

        for (int i = 0; i < rectangles.length; i++) {
            Rectangle r = new Rectangle(122, 11);
            r.setLayoutX(83.0 + (i * 122));
            r.setLayoutY(120.0);
            r.setOpacity(0.0);
            r.setFill(rectangleColors[i]);

            rectangles[i] = r;
        }

        // final Node footer = getFooterPanel();

        header.setLayoutX(0);
        header.setLayoutY(0);

        buildLogo();
        buildPage();

        getRootNode().getChildren().addAll(eclipseCONLogo, jrebirthLogo,/* footer, */this.slideContentPane, header);
        getRootNode().getChildren().addAll(rectangles);

    }

    private void buildPage() {

        this.page3Petals = ImageViewBuilder.create()
                .layoutX(900) // 900 32
                .layoutY(25) // 231
                .translateX(200)
                .image(EcImages.PAGE_3_PETALS.get())
                .effect(GlowBuilder.create().level(0.2).build())
                .build();

        this.pageLabel = LabelBuilder.create()
                .layoutX(932)
                .layoutY(42)
                .text(String.valueOf(getModel().getSlide().getPage()))
                .font(PrezFonts.PAGE.get())
                .textFill(rectangleColors[3])
                .scaleX(0).scaleY(0)
                .build();

        this.page1Petal = ImageViewBuilder.create()
                .layoutX(960) // 32
                .layoutY(16) // 231
                .translateX(200)
                .translateY(-200)
                .image(EcImages.PAGE_1_PETAL.get())
                .effect(GlowBuilder.create().level(0.2).build())
                .build();

        this.pageRays = ImageViewBuilder.create()
                .layoutX(890) // 32
                .layoutY(30) // 231
                .opacity(0)
                .effect(BloomBuilder.create().threshold(0.7).build())
                .image(EcImages.PAGE_RAYS.get())
                .build();

        this.pageSpark = ImageViewBuilder.create()
                .layoutX(960) // 32
                .layoutY(8) // 231
                .image(EcImages.PAGE_SPARK.get())
                .effect(BloomBuilder.create().threshold(1.0).build())
                .opacity(0)
                .scaleX(0).scaleY(0)
                .build();

        getRootNode().getChildren().addAll(page3Petals, pageLabel, page1Petal, pageRays, pageSpark);

    }

    /**
     * TODO To complete.
     */
    private void buildLogo() {
        this.jrebirthLogo = ImageViewBuilder.create()
                .layoutX(30)
                .layoutY(1030)// 530
                .image(EcImages.JREBIRTH_LOGO.get())
                .build();

        this.eclipseCONLogo = ImageViewBuilder.create()
                .layoutX(943.0)
                .layoutY(1030) // 530
                .image(EcImages.ECLIPSECON_LOGO.get())
                .build();
    }

    /**
     * Show en aempty slide.
     */
    protected void showEmptySlide() {
        this.subSlides.add(getModel().getStepPosition(), null);
    }

    /**
     * Add a subslide node.
     * 
     * @param defaultSubSlide the subslide node
     */
    private void addSubSlide(final Node defaultSubSlide) {

        this.subSlides.add(getModel().getStepPosition(), defaultSubSlide);
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

        pageLabel.setScaleX(0);
        pageLabel.setScaleY(0);

        page1Petal.setTranslateX(200);
        page1Petal.setTranslateY(-200);

        pageRays.setOpacity(0.0);

        pageSpark.setScaleX(0);
        pageSpark.setScaleY(0);
        pageSpark.setOpacity(0);

        headerRectangle.setTranslateX(-1100);
        headerPatternGroup.setTranslateY(-150);

        this.eclipseCONLogo.setTranslateY(500);
        this.jrebirthLogo.setTranslateY(500);

        for (Rectangle r : rectangles) {
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

        ParallelTransitionBuilder.create().children(

                // 100-700
                buildContentAnimation(),

                // 200 -700
                buildHeaderAnimation(),

                // 400-1900
                buildPageAnimation(),

                // 1000-1600
                FadeTransitionBuilder.create()
                        .node(this.primaryTitle)
                        .delay(Duration.millis(1000))
                        .duration(Duration.millis(600))
                        .fromValue(0)
                        .toValue(1.0)
                        .build(),
                // 1200-1800
                FadeTransitionBuilder.create()
                        .node(this.secondaryTitle)
                        .delay(Duration.millis(1200))
                        .duration(Duration.millis(600))
                        .fromValue(0)
                        .toValue(1.0)
                        .build(),
                // 1400-1700
                TranslateTransitionBuilder.create()
                        .node(this.eclipseCONLogo)
                        .delay(Duration.millis(1400))
                        .duration(Duration.millis(300))
                        .toY(-500)
                        .build(),
                // 1700-2000
                TranslateTransitionBuilder.create()
                        .node(this.jrebirthLogo)
                        .delay(Duration.millis(1700))
                        .duration(Duration.millis(300))
                        .toY(-500)
                        .build()

                )
                .build().play();
    }

    private Animation buildHeaderAnimation() {
        return
        // Step 1 rotate and translate
        ParallelTransitionBuilder.create()
                .delay(Duration.millis(200))
                .children(
                        TranslateTransitionBuilder.create()
                                .node(headerRectangle)
                                .byX(1100)
                                .duration(Duration.millis(500))
                                .interpolator(Interpolator.EASE_IN)
                                .build(),
                        TranslateTransitionBuilder.create()
                                .node(headerPatternGroup)
                                .byY(150)
                                .duration(Duration.millis(500))
                                .interpolator(Interpolator.EASE_IN)
                                .build()
                ).build();
    }

    private Animation buildPageAnimation() {
        return SequentialTransitionBuilder.create()
                .delay(Duration.millis(400))
                .children(
                        // Step 1 rotate and translate
                        ParallelTransitionBuilder.create()
                                .node(page3Petals)
                                .children(
                                        RotateTransitionBuilder.create()
                                                .byAngle(1080)
                                                .duration(Duration.millis(500))
                                                .interpolator(Interpolator.EASE_IN)
                                                .build(),
                                        TranslateTransitionBuilder.create()
                                                .byX(-200)
                                                .duration(Duration.millis(400))
                                                .interpolator(Interpolator.EASE_IN)
                                                .build()
                                ).build(),

                        // Step 2 zoomin page, translate mono petal
                        ParallelTransitionBuilder.create()
                                .children(
                                        ScaleTransitionBuilder.create()
                                                .node(pageLabel)
                                                .fromX(0).fromY(0)
                                                .toX(1.0).toY(1.0)
                                                .duration(Duration.millis(400))
                                                .build(),
                                        TranslateTransitionBuilder.create()
                                                .node(page1Petal)
                                                .byX(-200)
                                                .byY(200)
                                                .duration(Duration.millis(300))
                                                .interpolator(Interpolator.EASE_IN)
                                                .build()
                                ).build(),

                        // step3 fade in rays
                        FadeTransitionBuilder.create().node(pageRays)
                                .fromValue(0.0).toValue(1.0)
                                .duration(Duration.millis(300))
                                .build(),

                        // step 4 zoom in and fadein spark
                        ParallelTransitionBuilder.create()
                                .node(pageSpark)
                                .children(
                                        FadeTransitionBuilder.create()
                                                .fromValue(0.0).toValue(1.0)
                                                .duration(Duration.millis(300))
                                                .build(),
                                        ScaleTransitionBuilder.create()
                                                .fromX(0).fromY(0)
                                                .toX(1.0).toY(1.0)
                                                .duration(Duration.millis(200))
                                                .build()
                                )

                                .build()
                )

                .build();
    }

    /**
     * TODO To complete.
     * 
     * @return
     */
    private SequentialTransition buildContentAnimation() {
        return SequentialTransitionBuilder.create()
                .children(
                        ParallelTransitionBuilder
                                .create()
                                .delay(Duration.millis(100))
                                .children(
                                        TranslateTransitionBuilder.create()
                                                .duration(Duration.millis(600))
                                                .fromX(1024 / 2)
                                                .fromY(768 / 2)
                                                .toX(0)
                                                .toY(0)
                                                .build(),
                                        ScaleTransitionBuilder
                                                .create()
                                                .duration(Duration.millis(600))
                                                .fromX(0)
                                                .fromY(0)
                                                .toX(1)
                                                .toY(1)
                                                .build()
                                )
                                .node(this.slideContentPane)
                                .build(),
                        displayRectangle()
                ).build();
    }

    private Animation displayRectangle() {
        SequentialTransition rectAnimation = SequentialTransitionBuilder.create()
                // .delay(Duration.millis(200))
                .build();

        for (Rectangle r : rectangles) {

            rectAnimation.getChildren().add(
                    FadeTransitionBuilder.create()
                            .node(r)
                            .duration(Duration.millis(200))
                            .fromValue(0.0)
                            .toValue(1.0)
                            .build()
                    );
        }

        return rectAnimation;
    }

    /**
     * TODO To complete.
     * 
     * @param node the node
     */
    protected void bindNode(final Node node) {
        node.scaleXProperty().bind(bindWidth());
        node.scaleYProperty().bind(bindHeight());
    }

    /**
     * TODO To complete.
     * 
     * @return the number binding
     */
    protected NumberBinding bindHeight() {
        return Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().heightProperty(), 768);
    }

    /**
     * TODO To complete.
     * 
     * @return the number binding
     */
    protected NumberBinding bindWidth() {
        return Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().widthProperty(), 1024);
    }

    /**
     * Build the default content slide.
     * 
     * @param slideContent the slide content
     * @return the vbox with default content items
     */
    protected VBox buildDefaultContent(final SlideContent slideContent) {

        if (slideContent.getTitle() != null && secondaryTitle != null) {
            this.secondaryTitle.setText(slideContent.getTitle());
        }

        // this.prezTitle.setText("JavaFX 2.2\n What's Up ?");

        final VBox vbox = new VBox();
        // vbox.getStyleClass().add("content");

        if (getModel().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(getModel().getSlide().getStyle());
        }

        if (slideContent != null) {
            for (final SlideItem item : slideContent.getItem()) {
                addSlideItem(vbox, item);
            }
        }

        return vbox;
    }

    /**
     * Add a slide item by managing level.
     * 
     * @param vbox the layout node
     * @param item the slide item to add
     */
    protected void addSlideItem(final VBox vbox, final SlideItem item) {

        Node node = null;
        if (item.getLink()) {

            final Hyperlink link = HyperlinkBuilder.create()
                    .opacity(1.0)
                    .text(item.getValue())
                    .build();

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

        } else if (item.getHtml()) {

            final WebView web = WebViewBuilder.create()
                    .fontScale(1.4)
                    // .effect(ReflectionBuilder.create().fraction(0.4).build())
                    .build();
            web.getEngine().loadContent(item.getValue());

            VBox.setVgrow(web, Priority.NEVER);

            node = web; // StackPaneBuilder.create().children(web).style("-fx-border-width:2;-fx-border-color:#000000").build();

        } else if (item.getImage() != null) {

            final Image image = loadImage(item.getImage());
            final ImageView imageViewer = ImageViewBuilder.create()
                    .styleClass(ITEM_CLASS_PREFIX + item.getLevel())
                    .image(image)
                    // .effect(ReflectionBuilder.create().fraction(0.9).build())
                    .build();

            node = imageViewer;
        } else {

            final Text text = TextBuilder.create()
                    .styleClass(ITEM_CLASS_PREFIX + item.getLevel())
                    .text(item.getValue() == null ? "" : item.getValue())
                    .build();

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

    /**
     * Show the slide step store which match with xml file.
     * 
     * @param slideStep the slide step to show
     */
    public void showSlideStep(final SlideStep slideStep) {

        if (this.subSlides.size() >= getModel().getStepPosition() || this.subSlides.get(getModel().getStepPosition()) == null) {
            addSubSlide(buildDefaultContent(getModel().getContent(slideStep)));
        }
        final Node nextSlide = this.subSlides.get(getModel().getStepPosition());

        if (this.currentSubSlide != null && nextSlide != null) {
            performStepAnimation(nextSlide);
        } else {
            // No Animation
            this.currentSubSlide = nextSlide;
        }

    }

    /**
     * Show custom slide step.
     * 
     * @param node the node
     */
    protected void showCustomSlideStep(final Node node) {

        addSubSlide(node);
        final Node nextSlide = this.subSlides.get(getModel().getStepPosition());
        if (this.currentSubSlide != null && nextSlide != null) {

            performStepAnimation(nextSlide);
        } else {
            // No Animation
            this.currentSubSlide = nextSlide;
        }
    }

    /**
     * TODO To complete.
     * 
     * @param nextSlide the next slide
     */
    private void performStepAnimation(final Node nextSlide) {

        setSlideLocked(true);
        this.subSlideTransition = ParallelTransitionBuilder.create()

                .onFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(final ActionEvent event) {
                        AbstractBaseView.this.currentSubSlide = nextSlide;
                        AbstractBaseView.this.setSlideLocked(false);
                    }
                })

                .children(
                        SequentialTransitionBuilder.create()
                                .node(this.currentSubSlide)
                                .children(
                                        TranslateTransitionBuilder.create()
                                                .duration(Duration.millis(400))
                                                .fromY(0)
                                                .toY(-700)
                                                // .fromZ(-10)
                                                .build(),
                                        TimelineBuilder.create()
                                                .keyFrames(
                                                        new KeyFrame(Duration.millis(0), new KeyValue(this.currentSubSlide.visibleProperty(), true)),
                                                        new KeyFrame(Duration.millis(1), new KeyValue(this.currentSubSlide.visibleProperty(), false))
                                                )
                                                .build()
                                )

                                .build(),
                        SequentialTransitionBuilder.create()
                                .node(nextSlide)
                                .children(
                                        TimelineBuilder.create()
                                                .keyFrames(
                                                        new KeyFrame(Duration.millis(0), new KeyValue(nextSlide.visibleProperty(), false)),
                                                        new KeyFrame(Duration.millis(1), new KeyValue(nextSlide.visibleProperty(), true))
                                                )
                                                .build(),
                                        TranslateTransitionBuilder.create()
                                                .duration(Duration.millis(400))
                                                .fromY(700)
                                                .toY(0)
                                                // .fromZ(-10)
                                                .build()
                                )
                                .build()
                )
                .build();
        this.subSlideTransition.play();

    }

    /**
     * Gets the slide content.
     * 
     * @return Returns the slideContent.
     */
    protected StackPane getSlideContent() {
        return this.slideContentPane;
    }

    /**
     * Build and return the header panel.
     * 
     * @return the header panel
     */
    protected Node getHeaderPanel() {

        final Pane headerPane = PaneBuilder.create()
                .styleClass("header")
                .layoutX(0.0)
                .layoutY(0.0)
                .minWidth(1024)
                .prefWidth(1024)
                .build();

        this.primaryTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(EcFonts.SLIDE_TITLE.get())
                .textFill(EcColors.SLIDE_TITLE.get())
                .text(getModel().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"))
                .layoutX(100)
                .layoutY(16)
                .opacity(0)
                .effect(BloomBuilder.create().threshold(1.0).input(
                        DropShadowBuilder.create()
                                .blurType(BlurType.TWO_PASS_BOX)
                                .color(EcColors.SLIDE_TITLE_GLOW.get())
                                .radius(8)
                                .spread(0.5)
                                .build())
                        .build())

                // .style("-fx-background-color:#CCCB20")
                .build();

        this.secondaryTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(EcFonts.SLIDE_SUB_TITLE.get())
                .textFill(EcColors.SLIDE_TITLE.get())
                .opacity(0)
                .effect(BloomBuilder.create().threshold(1.0).input(
                        DropShadowBuilder.create()
                                .blurType(BlurType.TWO_PASS_BOX)
                                .color(EcColors.SLIDE_TITLE_GLOW.get())
                                .radius(4)
                                .spread(0.6)
                                .build())
                        .build())
                .layoutX(100)
                .layoutY(70)
                .minWidth(450)
                // .style("-fx-background-color:#E53B20")
                .alignment(Pos.CENTER_LEFT)
                .textAlignment(TextAlignment.RIGHT)
                .build();

        // this.prezTitle = LabelBuilder.create()
        // // .styleClass("slideTitle")
        // .font(EcFonts.PREZ_TITLE.get())
        // .textFill(Color.LIGHTGRAY)
        // // .scaleX(1.5)
        // // .scaleY(1.5)
        // // .layoutX(545)
        // // .layoutY(711)
        // .layoutX(480)
        // .layoutY(14.0)
        // .minWidth(450)
        // // .style("-fx-background-color:#E53B20")
        // .alignment(Pos.CENTER_RIGHT)
        // .textAlignment(TextAlignment.RIGHT)
        // .build();

        this.headerRectangle = RectangleBuilder.create()
                .layoutX(0)
                .layoutY(0)
                .width(860)
                .height(115.0)
                .translateX(-1100)
                // .transforms(RotateBuilder.create().pivotX(860).pivotY(115).angle(-180).build())
                .fill(Color.BLACK)
                .build();

        headerPattern1 = ImageViewBuilder.create()
                .image(EcImages.HEADER_PATTERN.get())
                .blendMode(BlendMode.SRC_OVER)
                .opacity(0.4)
                .layoutX(-164)
                .layoutY(0)
                .build();

        headerPattern2 = ImageViewBuilder.create()
                .image(EcImages.HEADER_PATTERN.get())
                .blendMode(BlendMode.SRC_OVER)
                .opacity(0.2)
                .layoutX(861)
                .layoutY(0)
                .build();

        headerPatternGroup = GroupBuilder.create()
                .translateY(-150)
                .children(headerPattern1, headerPattern2)
                .build();

        headerPane.getChildren().addAll(/* this.topRectangle, */this.headerRectangle, headerPatternGroup,
                // this.bigPokemon, this.smallPokemon,
                this.primaryTitle, this.secondaryTitle
                );

        return headerPane;

    }

    /**
     * Build and return the content panel.
     * 
     * @return the content panel
     */
    protected Node getContentPanel() {
        return buildDefaultContent(getModel().getDefaultContent());
    }

    /**
     * Build and return the footer panel.
     * 
     * @return the footer panel
     */
    protected Node getFooterPanel() {
        this.pageLabel = LabelBuilder.create()
                .text(String.valueOf(getModel().getSlide().getPage()))
                .font(PrezFonts.PAGE.get())
                .build();

        final AnchorPane ap = AnchorPaneBuilder.create()
                .children(this.pageLabel)
                .build();
        AnchorPane.setRightAnchor(this.pageLabel, 20.0);

        final StackPane sp = StackPaneBuilder.create()
                .styleClass("footer")
                .prefHeight(35.0)
                .minHeight(Region.USE_PREF_SIZE)
                .maxHeight(Region.USE_PREF_SIZE)
                .children(ap)
                .build();

        StackPane.setAlignment(ap, Pos.CENTER_RIGHT);

        return sp;
    }
}
