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
package org.jrebirth.demo.masteringtables.beans;

/**
 * The Enum Operator.
 */
public enum Operator {

    /** The subtraction. */
    subtraction,

    /** The addition. */
    addition,

    /** The multiplication. */
    multiplication,

    /** The division. */
    division;

    /**
     * Instantiates a new operator.
     */
    Operator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String res;
        switch (this) {
            case subtraction:
                res = "-";
                break;
            case addition:
                res = "+";
                break;
            case multiplication:
                res = "x";
                break;
            case division:
                res = "÷";
                break;
            default:
                res = "";

        }
        return res;
    }
}