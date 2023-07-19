package model.interfaces;

public interface OnlinePaymentService {

	public double paymentFee(double amount); //taxa de pagamento
	public double interest(double amount, int months); //juros
	
}
