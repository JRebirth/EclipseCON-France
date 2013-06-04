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
package org.jrebirth.presentation.eclipsecon.resources;

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.color.ColorItem;
import org.jrebirth.core.resource.color.ColorParams;
import org.jrebirth.core.resource.color.WebColor;

// TODO: Auto-generated Javadoc
/**
 * The class <strong>EcColors</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum EcColors implements ColorItem {

    /** Color for slide title, white. */
    SLIDE_TITLE(new WebColor("3CBFBB", 1.0)), //

    /** Color for slide title, white. */
    SLIDE_TITLE_GLOW(new WebColor("0BF4ED", 0.7)),

    RECTANGLE_1(new WebColor("D9CC21")),
    RECTANGLE_2(new WebColor("F7741C")),
    RECTANGLE_3(new WebColor("C29D68")),
    RECTANGLE_4(new WebColor("665E4C")),
    RECTANGLE_5(new WebColor("7E8F83")),
    RECTANGLE_6(new WebColor("9FD9DF")),
    RECTANGLE_7(new WebColor("0BF4ED")),

    /** The shape blue. */
    SHAPE_BLUE(new WebColor("3495CE", 1.0)),

    /** The drop shadow. */
    DROP_SHADOW(new WebColor("000000", 0.8)),

    /** The inner shadow. */
    INNER_SHADOW(new WebColor("FFFFFF", 0.3)),

    /** The GRADIEN t_1. */
    GRADIENT_1(new WebColor("1AA2AC", 1.0)),

    /** The GRADIEN t_2. */
    GRADIENT_2(new WebColor("F04F24", 1.0)),

    /** The GRADIEN t_3. */
    GRADIENT_3(new WebColor("FFF200", 1.0))

    ;

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private EcColors(final ColorParams colorParams) {
        builder().storeParams(this, colorParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorBuilder builder() {
        return ResourceBuilders.COLOR_BUILDER;
    }
}
