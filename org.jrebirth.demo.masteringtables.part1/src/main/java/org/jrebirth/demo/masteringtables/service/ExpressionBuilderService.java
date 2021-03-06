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
package org.jrebirth.demo.masteringtables.service;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveTypeBase;
import org.jrebirth.demo.masteringtables.beans.Expression;
import org.jrebirth.demo.masteringtables.beans.ExpressionBuilder;
import org.jrebirth.demo.masteringtables.beans.Operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Service ExpressionBuilder is used to build all mathematical tables.
 */
public final class ExpressionBuilderService extends ServiceBase {

    /** The Wave Item ALL_EXPRESSIONS. */
    public static final WaveItem<List<Expression>> ALL_EXPRESSIONS = new WaveItem<List<Expression>>() {
    };

    /** The Wave Type DO_BUILD_TABLES. */
    public static final WaveTypeBase DO_BUILD_TABLES = WaveTypeBase.build("BUILD_TABLES");

    /** The Wave Type RE_TABLES_BUILT. */
    public static final WaveTypeBase RE_TABLES_BUILT = WaveTypeBase.build("TABLES_BUILT");

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionBuilderService.class);

    /** The multiplication table. */
    private final List<Expression> multiplicationTable = new ArrayList<>();

    /** The addition table. */
    private final List<Expression> additionTable = new ArrayList<>();

    /** The subtraction table. */
    private final List<Expression> subtractionTable = new ArrayList<>();

    /** The division table. */
    private final List<Expression> divisionTable = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        // Define the service method
        registerCallback(DO_BUILD_TABLES, RE_TABLES_BUILT);
    }

    /**
     * Builds all tables.
     * 
     * @param wave the wave
     */
    public void doBuildTables(final Wave wave) {

        LOGGER.trace("Build Tables.");

        for (int leftOperand = 1; leftOperand <= 12; leftOperand++) {

            for (int rightOperand = 1; rightOperand <= 12; rightOperand++) {

                this.multiplicationTable.add(ExpressionBuilder.create()
                        .left(leftOperand).operator(Operator.multiplication).right(rightOperand)
                        .result(leftOperand * rightOperand).build());

                this.additionTable.add(ExpressionBuilder.create()
                        .left(leftOperand).operator(Operator.addition).right(rightOperand)
                        .result(leftOperand + rightOperand).build());

                this.divisionTable.add(ExpressionBuilder.create()
                        .left(rightOperand * leftOperand).operator(Operator.division).right(leftOperand)
                        .result(rightOperand).build());
            }

        }

        int tableGap = 0;
        for (int rightOperand = 0; rightOperand <= 11; rightOperand++) {

            for (int leftOperand = tableGap; leftOperand <= tableGap + 12; leftOperand++) {

                this.subtractionTable.add(ExpressionBuilder.create()
                        .left(leftOperand).operator(Operator.subtraction).right(rightOperand)
                        .result(leftOperand - rightOperand).build());
            }
            tableGap++;
        }

        LOGGER.trace("Tables are ready !");
    }

    /**
     * Gets the multiplication table.
     * 
     * @return the multiplication table
     */
    public List<Expression> getMultiplicationTable() {
        return this.multiplicationTable;
    }

    /**
     * Gets the addition table.
     * 
     * @return the addition table
     */
    public List<Expression> getAdditionTable() {
        return this.additionTable;
    }

    /**
     * Gets the subtraction table.
     * 
     * @return the subtraction table
     */
    public List<Expression> getSubtractionTable() {
        return this.subtractionTable;
    }

    /**
     * Gets the division table.
     * 
     * @return the division table
     */
    public List<Expression> getDivisionTable() {
        return this.divisionTable;
    }

}
