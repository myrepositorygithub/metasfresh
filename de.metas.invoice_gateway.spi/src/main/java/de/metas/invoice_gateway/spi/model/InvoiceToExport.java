package de.metas.invoice_gateway.spi.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import javax.annotation.Nullable;

import java.time.Instant;
import java.util.GregorianCalendar;
import java.util.List;

import de.metas.invoice_gateway.spi.CustomInvoicePayload;

/*
 * #%L
 * metasfresh-invoice.gateway.commons
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@Value
@Builder(toBuilder = true)
public class InvoiceToExport
{
	public static final String CURRENCY_CHF = "CHF";

	@NonNull
	InvoiceId id;

	@NonNull
	MetasfreshVersion metasfreshVersion;

	@NonNull
	GregorianCalendar invoiceDate;

	@NonNull
	Instant invoiceTimestamp;

	@NonNull
	String documentNumber;

	@NonNull
	BPartner biller;

	@NonNull
	BPartner recipient;

	boolean isReversal;

	@NonNull
	Money amount;

	@NonNull
	Money alreadyPaidAmount;

	@Nullable
	CustomInvoicePayload customInvoicePayload;

	@Singular("invoiceTax")
	List<InvoiceTax> invoiceTaxes;

	@Singular
	List<InvoiceLine> invoiceLines;

	@Singular
	List<InvoiceAttachment> invoiceAttachments;

}
