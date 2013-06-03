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

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.image.ImageItem;
import org.jrebirth.core.resource.image.LocalImage;

/**
 * The class <strong>EcImages</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public interface EcImages {

    ImageItem ECLIPSECON_LOGO = create(new LocalImage("images/base/Logo_EclipseCON.png"));

    ImageItem JREBIRTH_LOGO = create(new LocalImage("images/base/Logo_JRebirth.png"));

    ImageItem HEADER_PATTERN = create(new LocalImage("images/base/Header_Pattern.png"));

    ImageItem PAGE_3_PETALS = create(new LocalImage("images/base/Page_3_Petals.png"));
    ImageItem PAGE_1_PETAL = create(new LocalImage("images/base/Page_1_Petal.png"));
    ImageItem PAGE_SPARK = create(new LocalImage("images/base/Page_Spark.png"));
    ImageItem PAGE_RAYS = create(new LocalImage("images/base/Page_Rays.png"));
}
