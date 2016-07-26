package de.metas.edi.api;

/*
 * #%L
 * de.metas.edi
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.util.Collection;
import java.util.Properties;

import org.adempiere.util.ISingletonService;

import de.metas.edi.model.I_C_Order;
import de.metas.edi.model.I_C_OrderLine;
import de.metas.edi.model.I_M_InOut;
import de.metas.edi.model.I_M_InOutLine;
import de.metas.esb.edi.model.I_EDI_Desadv;
import de.metas.esb.edi.model.I_EDI_DesadvLine;
import de.metas.esb.edi.model.I_EDI_DesadvLine_SSCC;

public interface IDesadvBL extends ISingletonService
{
	/**
	 * Creates a new desadv for the given <code>order</code>'s POReference, or retrieves an existing one. <br>
	 * If a new one is created, then values are taken from this order.<br>
	 * Also iterates the order's lines and creates {@link I_EDI_DesadvLine}s for their respective line numbers unless such desadv lines already exist.
	 * <p>
	 * Notes:
	 * <ul>
	 * <li>the order and its lines are modified (their referencing/FK columns are set), but only the lines are saved! This is because we call this method from a C_Order modelvalidator.
	 * <li>Assumes that the given order has a non-empty <code>POReference</code>.
	 * </ul>
	 * 
	 * @param inOut
	 * @return
	 */
	I_EDI_Desadv addToDesadvCreateIfNotExistForOrder(I_C_Order order);

	/**
	 * Removes the given <code>order</code> from its desadv (if any) and also removes its order lines from the desadv lines.
	 * <p>
	 * If because of that the desadv lines in question don't have any assigned order line left, they are deleted
	 * 
	 * @param order
	 */
	void removeOrderFromDesadv(I_C_Order order);

	/**
	 * Removes/detaches the given inOutLine from its desadv line (if any). If after this, no order lines are referencing the desadv line, then it is deleted
	 * 
	 * @param inOutLine
	 */
	void removeOrderLineFromDesadv(I_C_OrderLine orderLine);

	/**
	 * For existing desadv lines, just <code>QtyEntered</code> and <code>MovementQty</code>. are updated.
	 * Note that this method also sets the desadv(-line)s' IDs to the inOut and its lines and saves them.
	 * 
	 * @param inOut
	 * @return
	 */
	I_EDI_Desadv addToDesadvCreateIfNotExistForInOut(I_M_InOut inOut);

	/**
	 * Removes the given <code>inOut</code> from its desadv (if any) and also removes its inOut lines from the desadv lines.
	 * <p>
	 * Note: the inout and its lines are modified, but only the lines are saved! This is because we call this method from an M_InOut modelvalidator.
	 * 
	 * @param inOut
	 */
	void removeInOutFromDesadv(I_M_InOut inOut);

	/**
	 * Removes/detaches the given inOutLine from its desadv line (if any) and subtracts the inout line's MovementQty from the desadv line's qtys
	 * 
	 * @param inOutLine
	 */
	void removeInOutLineFromDesadv(I_M_InOutLine inOutLine);

	/**
	 * Print SSCC18 labels for given {@link I_EDI_DesadvLine_SSCC} IDs.
	 * 
	 * @param ctx
	 * @param desadvLineSSCC_IDs_ToPrint
	 * @task 08910
	 */
	void printSSCC18_Labels(Properties ctx, Collection<Integer> desadvLineSSCC_IDs_ToPrint);

	/**
	 * Set the current minimum sum percentage taken from the sys config 'de.metas.esb.edi.DefaultMinimumPercentage'
	 * 
	 * @param desadv
	 */
	void setMinimumPercentage(I_EDI_Desadv desadv);
}
