package lotto;

import java.util.List;

import lotto.domains.customer.Customer;
import lotto.domains.lotto.LottoPrizeNumbers;
import lotto.domains.lotto.LottoTicket;
import lotto.domains.lotto.LottoTicketMachine;
import lotto.service.LottoService;
import lotto.util.TypeConverter;
import lotto.view.InputInterface;
import lotto.view.OutputInterface;

public class Application {
	public static void main(String[] args) {
		InputInterface inputInterface = new InputInterface();
		LottoService lottoService = new LottoService();

		Customer customer = purchaseLottoTickets(inputInterface);
		int ticketAmount = customer.calculateAmount();
		OutputInterface.printMessage(ticketAmount + OutputInterface.PURCHASE_AMOUNT.toString());

		LottoTicketMachine lottoTicketMachine = LottoTicketMachine.from(ticketAmount);

		LottoTicket tickets = lottoTicketMachine.generateLottoTickets();
		OutputInterface.printMessage(tickets.toString());
		OutputInterface.printNewLine();

		List<Integer> winningNumbers = drawWinningNumbers(inputInterface, lottoService);
		int bonusNumber = drawBonusNumber(inputInterface);

		LottoPrizeNumbers lottoPrizeNumbers = LottoPrizeNumbers.of(winningNumbers, bonusNumber);
		OutputInterface.printNewLine();

	}

	private static Customer purchaseLottoTickets(InputInterface inputInterface) {
		while (true) {
			try {
				OutputInterface.printMessage(OutputInterface.ENTER_PURCHASE_PRICE);
				String price = inputInterface.readLine();
				OutputInterface.printNewLine();

				return Customer.from(TypeConverter.convertStringToInteger(price));
			} catch (IllegalArgumentException exception) {
				processException(exception);
			}
		}
	}

	private static List<Integer> drawWinningNumbers(InputInterface inputInterface, LottoService lottoService) {
		while (true) {
			try {
				OutputInterface.printMessage(OutputInterface.ENTER_WINNING_NUMBERS);
				String winningNumbers = inputInterface.readLine();

				return lottoService.drawWinningNumbers(winningNumbers);
			} catch (IllegalArgumentException exception) {
				processException(exception);
			}
		}
	}

	private static int drawBonusNumber(InputInterface inputInterface) {
		while (true) {
			try {
				OutputInterface.printMessage(OutputInterface.ENTER_BONUS_NUMBER);
				String bonusNumber = inputInterface.readLine();

				return TypeConverter.convertStringToInteger(bonusNumber);
			} catch (IllegalArgumentException exception) {
				processException(exception);
			}
		}
	}

	private static void processException(IllegalArgumentException exception) {
		OutputInterface.printMessage(exception.getMessage());
		OutputInterface.printNewLine();
	}
}
