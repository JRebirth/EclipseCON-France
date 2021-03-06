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
package org.jrebirth.af.presentation.eclipsecon.resources;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.image.ImageExtension;
import org.jrebirth.af.api.resource.image.ImageItem;
import org.jrebirth.af.core.resource.image.RelImage;

/**
 * The class <strong>EcImages</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public interface EcImages {

    ImageItem ECLIPSECON_LOGO = create(new RelImage("base", "Logo_EclipseCON", ImageExtension.PNG));

    ImageItem JREBIRTH_LOGO = create(new RelImage("base", "Logo_JRebirth", ImageExtension.PNG));

    ImageItem HEADER_PATTERN = create(new RelImage("base", "Header_Pattern", ImageExtension.PNG));

    ImageItem PAGE_3_PETALS = create(new RelImage("base", "Page_3_Petals", ImageExtension.PNG));
    ImageItem PAGE_1_PETAL = create(new RelImage("base", "Page_1_Petal", ImageExtension.PNG));
    ImageItem PAGE_SPARK = create(new RelImage("base", "Page_Spark", ImageExtension.PNG));
    ImageItem PAGE_RAYS = create(new RelImage("base", "Page_Rays", ImageExtension.PNG));

    ImageItem CUSTOM_CAR = create(new RelImage("slides/fx", "Custom_Car", ImageExtension.JPG));
    ImageItem TEXTURE = create(new RelImage("slides/fx", "fabric_texture_car", ImageExtension.JPG));

    ImageItem PLACE_LOGO = create(new RelImage("PlaceLogo", ImageExtension.JPG));

}
