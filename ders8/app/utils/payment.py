# app/utils/payment.py
import stripe
from app.core.config import settings

stripe.api_key = settings.STRIPE_SECRET_KEY

def create_payment_intent(amount: float, currency: str = "usd"):
    """Create Stripe payment intent"""
    try:
        intent = stripe.PaymentIntent.create(
            amount=int(amount * 100),  # Convert to cents
            currency=currency,
            payment_method_types=["card"],
        )
        return intent
    except stripe.error.StripeError as e:
        raise ValueError(f"Payment error: {str(e)}")

def confirm_payment(payment_intent_id: str):
    """Confirm payment"""
    try:
        intent = stripe.PaymentIntent.confirm(payment_intent_id)
        return intent.status == "succeeded"
    except stripe.error.StripeError:
        return False

# Simplified simulation for demo
def simulate_payment(amount: float) -> bool:
    """Simulate payment processing"""
    # In real implementation, use Stripe API
    return True  # Always successful in demo