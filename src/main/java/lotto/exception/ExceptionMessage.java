package lotto.exception;

public enum ExceptionMessage {
	NOT_EMPTY("빈 문자열은 입력할 수 없습니다."),
	NOT_NUMBER("숫자가 아닙니다."),
	MISMATCHED_REGEX("잘못된 입력입니다."),

	LOTTO_NUMBER_IS_DUPLICATED("중복된 번호는 입력할 수 없습니다."),
	INVALID_LOTTO_NUMBER_SIZE("로또 번호는 6개여야 합니다."),
	LOTTO_NUMBER_IS_OUT_OF_BOUND("로또 번호는 1~45까지만 발급가능합니다.");

	private final String message;
	private static final String ERROR_PREFIX = "[ERROR] ";

	ExceptionMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return ERROR_PREFIX + message;
	}
}
