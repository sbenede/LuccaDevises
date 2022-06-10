package com.lucca.devise;

import com.lucca.devise.model.Exchange;
import com.lucca.devise.utils.ConverterUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Converter {
	private final List<Exchange> exchangeToDoTmp = new ArrayList<>();
	private List<Exchange> exchangeToDo = new ArrayList<>();

	/**
	 * Get the shortest list of exchanges needed to do the conversion.
	 *
	 * @param exchanges list of exchanges
	 * @param from currency source
	 * @param to currency destination
	 * @return all exchanges to do the conversion
	 */
	public List<Exchange> getExchangeToDo(final List<Exchange> exchanges, final String from, final String to) {
		List<Exchange> conversionTmp = new ArrayList<>(exchanges);

		for (Exchange exchange : exchanges) {
			// We determine if one of the current currencies corresponds to the one we are looking for
			String currentCurrency = getCurrentCurrency(exchange, from);

			// If so, we deal with it
			if (StringUtils.isNotBlank(currentCurrency)) {
				exchangeToDoTmp.add(exchange);
				// If there are still exchanges to find, we recursively call the function to find the rest
				if (!Objects.equals(currentCurrency, to)) {
					// We remove the conversion found to continue searching
					conversionTmp.remove(exchange);
					getExchangeToDo(conversionTmp, currentCurrency, to);

				}
				else {
					// If the list of exchanges to be made is shorter than the previous one, it is updated
					if (CollectionUtils.isEmpty(exchangeToDo) || exchangeToDo.size() > exchangeToDoTmp.size()) {
						exchangeToDo = new ArrayList<>(exchangeToDoTmp);
					}
				}
				if (exchangeToDoTmp.size() > 0) {
					exchangeToDoTmp.remove(exchangeToDoTmp.size() - 1);
				}
			}
		}

		return exchangeToDo;
	}

	/**
	 * Get currency to deal with
	 *
	 * @param exchange exchange to test
	 * @param from currency source
	 * @return current currency to deal with
	 */
	private String getCurrentCurrency(final Exchange exchange, final String from) {
		if (exchange == null)
			return null;

		if (Objects.equals(exchange.getFromCurrency(), from)) {
			return exchange.getToCurrency();
		}
		else if (Objects.equals(exchange.getToCurrency(), from)) {
			return exchange.getFromCurrency();
		}

		return null;
	}

	/**
	 * Convert amount to final currency
	 *
	 * @param exchanges list of exchanges to use
	 * @param from currency source
	 * @param amount amount to convert
	 * @return amount in final currency
	 */
	public int convert(final List<Exchange> exchanges, final String from, final double amount) {
		if (CollectionUtils.isEmpty(exchanges)) {
			return 0;
		}

		// We apply the exchanges to be made
		String currentCurrency = from;
		double finalAmount = amount;

		for (Exchange conversion : exchanges) {
			if (Objects.equals(conversion.getFromCurrency(), currentCurrency)) {
				currentCurrency = conversion.getToCurrency();
				finalAmount = ConverterUtils.roundValue(finalAmount * conversion.getRate(), 4);
			}
			else if (Objects.equals(conversion.getToCurrency(), currentCurrency)) {
				// inverted conversion
				currentCurrency = conversion.getFromCurrency();
				finalAmount = ConverterUtils.roundValue(finalAmount / conversion.getRate(), 4);
			}
		}

		return (int) finalAmount;
	}
}
