package de.metas.handlingunits.material.interceptor;

import org.compiere.model.I_M_Transaction;

import com.google.common.annotations.VisibleForTesting;

import de.metas.material.event.commons.EventDescriptor;
import lombok.NonNull;

/*
 * #%L
 * de.metas.handlingunits.base
 * %%
 * Copyright (C) 2018 metas GmbH
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

public class TransactionDescriptorFactory
{
	@VisibleForTesting
	public TransactionDescriptor ofRecord(@NonNull final I_M_Transaction record)
	{
		return TransactionDescriptor
				.builder()
				.eventDescriptor(EventDescriptor.createNew(record))
				.productId(record.getM_Product_ID())
				.transactionId(record.getM_Transaction_ID())
				.warehouseId(record.getM_Locator().getM_Warehouse_ID())
				.movementDate(record.getMovementDate())
				.movementQty(record.getMovementQty())
				.costCollectorId(record.getPP_Cost_Collector_ID())
				.inoutLineId(record.getM_InOutLine_ID())
				.movementLineId(record.getM_MovementLine_ID())
				.inventoryLineId(record.getM_InventoryLine_ID())
				.movementType(record.getMovementType())
				.build();
	}
}
