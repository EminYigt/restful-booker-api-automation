package Tests;

import Base.BaseApiTest;
import Clients.AuthClient;
import Clients.BookingClient;
import Models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends BaseApiTest {

    private Booking createSampleBooking(String firstname,
                                        String lastname,
                                        int totalPrice,
                                        boolean depositPaid,
                                        String additionalNeeds){
        BookingDates dates = new BookingDates();
        dates.setCheckin("2026-06-14");
        dates.setCheckout("2026-06-20");

        Booking booking = new Booking();
        booking.setFirstname(firstname);
        booking.setLastname(lastname);
        booking.setTotalprice(totalPrice);
        booking.setDepositpaid(depositPaid);
        booking.setBookingdates(dates);
        booking.setAdditionalNeeds(additionalNeeds);
        return booking;
    }
    private String getToken(){
        AuthRequest ar = new AuthRequest();
        ar.setUsername("admin");
        ar.setPassword("password123");

        AuthClient ac = new AuthClient();
        AuthResponse authResponse = ac.createToken(ar);
        return authResponse.getToken();
    }

    @Test
    public void createBookingTest(){
        Booking booking =
                createSampleBooking("Yiğit", "Taşdelen",
                        500, true,
                "Breakfast");

        BookingClient bookingClient = new BookingClient();

        BookingResponse response = bookingClient.createBooking(booking);

        Assert.assertTrue(response.getBookingid() > 0);
        Assert.assertEquals(response.getBooking().getFirstname(), booking.getFirstname());
        Assert.assertEquals(response.getBooking().getLastname(), booking.getLastname());
    }

    @Test
    public void getBookingByIdTest(){
        Booking booking =
                createSampleBooking("Yiğit", "Taşdelen",
                        500, true,
                        "Breakfast");
        BookingClient bookingClient = new BookingClient();
        BookingResponse bookingResponse = bookingClient.createBooking(booking);
        int bookingId = bookingResponse.getBookingid();

        Booking fetchedBooking = bookingClient.getBookingById(bookingId);
        Assert.assertTrue(bookingId > 0);
        Assert.assertEquals(fetchedBooking.getFirstname(),
                booking.getFirstname());
        Assert.assertEquals(fetchedBooking.getLastname(),
                booking.getLastname());
        Assert.assertEquals(fetchedBooking.getTotalprice(),
                booking.getTotalprice());
    }
    @Test
    public void updateBookingTest(){
        String token = getToken();
        Booking booking =
                createSampleBooking("Yiğit", "Taşdelen",
                        500, true,
                        "Breakfast");
        BookingClient bookingClient = new BookingClient();
        BookingResponse bookingResponse = bookingClient.createBooking(booking);
        int bookingId = bookingResponse.getBookingid();
        booking.setFirstname("Emin Yiğit");
        booking.setTotalprice(1000);
        Booking updatedBooking =
                bookingClient.updateBooking(bookingId, booking, token);
        Booking fetchedBooking = bookingClient.getBookingById(bookingId);
        Assert.assertEquals(updatedBooking.getTotalprice(),
                fetchedBooking.getTotalprice());
        Assert.assertEquals(updatedBooking.getFirstname(),
                fetchedBooking.getFirstname());
    }
    @Test
    public void deleteBookingTest(){
        Booking booking =
                createSampleBooking("Yiğit", "Taşdelen",
                        500, true,
                        "Breakfast");
        String token = getToken();

        BookingClient bookingClient = new BookingClient();
        BookingResponse bookingResponse = bookingClient.createBooking(booking);
        int bookingId = bookingResponse.getBookingid();
        bookingClient.deleteBooking(bookingId, token);
        int st_code = bookingClient.getBookingStatusCode(bookingId);
        Assert.assertEquals(st_code, 404);
    }
}
