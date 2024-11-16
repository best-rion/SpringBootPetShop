package site.rion.PetShop.util;


import site.rion.PetShop.model.customerManage.Cart;
import site.rion.PetShop.model.customerManage.FoodOrder;
import site.rion.PetShop.model.customerManage.PetOrder;
import site.rion.PetShop.model.customerManage.SuppliesOrder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


 
public class PdfCreator
{
	
	public static ByteArrayOutputStream create(Cart cart) throws DocumentException, URISyntaxException, IOException
	{

		Document document = new Document();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

		document.open();
		
		// Header
		

		Paragraph header = new Paragraph("PET SHOP", new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD));
		header.setAlignment(Element.ALIGN_CENTER);
		header.setSpacingAfter(12.0f);
		
		document.add(header);
		
		// UserInfo
		
		String customerId = String.format("Customer ID    :    %d", cart.getCustomer().getId());
		Paragraph customerIdP = new Paragraph(customerId, new Font(Font.FontFamily.HELVETICA));
		customerIdP.setIndentationLeft(52.0f);
		document.add(customerIdP);
		
		String customerName = String.format("Customer Name:    %s", cart.getCustomer().getFull_name());
		Paragraph customerNameP = new Paragraph(customerName, new Font(Font.FontFamily.HELVETICA));
		customerNameP.setIndentationLeft(52.0f);
		document.add(customerNameP);
		
		String orderId = String.format("Cart ID:    %d", cart.getId());
		Paragraph orderIdP= new Paragraph(orderId, new Font(Font.FontFamily.HELVETICA));
		orderIdP.setSpacingAfter(20.0f);
		orderIdP.setIndentationLeft(52.0f);
		document.add(orderIdP);
		
		
		// Pet Table
		PdfPTable petTable = new PdfPTable(4);
		addPetTableHeader(petTable);
		
				// My Code
		int petCost = 0;
		
		for (PetOrder petOrder : cart.getPetOrders())
		{
			addRows(petTable, String.format("%d", petOrder.getPet().getId()));
			addRows(petTable, petOrder.getPet().getName());
			addRows(petTable, petOrder.getPet().getSpecies());
			

	        PdfPCell cell = new PdfPCell();
	        cell.setPhrase(new Phrase( String.format("%d", petOrder.getPet().getPrice())));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    petTable.addCell(cell);
			
			petCost += petOrder.getPet().getPrice();
		}

		addTranparentRows(petTable, "");
		addTranparentRows(petTable, "");
		addTranparentRows(petTable, "Pet Cost:");
		addTranparentRows(petTable, String.format("%d", petCost));
		

		petTable.setSpacingAfter(20.0f);
		
		
		document.add(petTable);
		
		
		
		// Food Table
		PdfPTable foodTable = new PdfPTable(4);
		addOtherTableHeader(foodTable);
		
				// My Code
		int foodCost = 0;
		
		for (FoodOrder foodOrder : cart.getFoodOrders())
		{
			addRows(foodTable, String.format("%d", foodOrder.getFood().getId()));
			addRows(foodTable, foodOrder.getFood().getName());
			addRows(foodTable, String.format("%d", foodOrder.getQuantity()));
			

	        PdfPCell cell = new PdfPCell();
	        cell.setPhrase(new Phrase( String.format("%d", foodOrder.getFood().getPrice())));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    foodTable.addCell(cell);
			
			foodCost += foodOrder.getFood().getPrice() * foodOrder.getQuantity();
		}

		addTranparentRows(foodTable, "");
		addTranparentRows(foodTable, "");
		addTranparentRows(foodTable, "Food Cost:");
		addTranparentRows(foodTable, String.format("%d", foodCost));


		foodTable.setSpacingAfter(20.0f);
		
		
		document.add(foodTable);
		
		
		
		
		// Supplies Table
		PdfPTable suppliesTable = new PdfPTable(4);
		addOtherTableHeader(suppliesTable);
		
				// My Code
		int suppliesCost = 0;
		
		for (SuppliesOrder suppliesOrder : cart.getSuppliesOrders())
		{
			addRows(suppliesTable, String.format("%d", suppliesOrder.getSupply().getId()));
			addRows(suppliesTable, suppliesOrder.getSupply().getName());
			addRows(suppliesTable, String.format("%d", suppliesOrder.getQuantity()));
			

	        PdfPCell cell = new PdfPCell();
	        cell.setPhrase(new Phrase( String.format("%d", suppliesOrder.getSupply().getPrice())));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    suppliesTable.addCell(cell);
			
			suppliesCost += suppliesOrder.getSupply().getPrice() * suppliesOrder.getQuantity();
		}

		addTranparentRows(suppliesTable, "");
		addTranparentRows(suppliesTable, "");
		addTranparentRows(suppliesTable, "Supplies Cost:");
		addTranparentRows(suppliesTable, String.format("%d", suppliesCost));
		

		suppliesTable.setSpacingAfter(20.0f);
		
		
		document.add(suppliesTable);
		
		
		// Total Table
		PdfPTable totalTable = new PdfPTable(4);

		addTranparentRows(totalTable, "");
		addTranparentRows(totalTable, "");
		addTranparentRows(totalTable, "Total Cost:");
		addTranparentRows(totalTable, String.format("%d", petCost + foodCost + suppliesCost));
		

		totalTable.setSpacingBefore(30.0f);
		
		
		document.add(totalTable);
		
		
		
		document.close();
		
		return outputStream;
	}
	
	private static void addPetTableHeader(PdfPTable table) {
	    Stream.of("ID", "Name", "Species", "Price")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        header.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(header);
	    });
	}
	
	private static void addOtherTableHeader(PdfPTable table) {
	    Stream.of("ID", "Name", "Quantity", "Price")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        header.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(header);
	    });
	}
	
	
	private static void addRows(PdfPTable table, String cell) {
	    table.addCell(cell);
	}
	
	private static void addTranparentRows(PdfPTable table, String content) {
		PdfPCell cell = new PdfPCell();
		cell.setBorderWidth(0);
		cell.setPhrase(new Phrase(content));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    table.addCell(cell);
	}
	
	private static void addImageToCell(PdfPTable table, Image img) throws URISyntaxException, BadElementException, MalformedURLException, IOException
	{
	    img.scalePercent(20);

	    PdfPCell imageCell = new PdfPCell(img);
	    imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    imageCell.setPadding(5.0f);
	    table.addCell(imageCell);
	}
	

	private static Image linkToImage(String link) throws IOException, BadElementException
	{
		URL url = new URL(link);
		BufferedImage img = ImageIO.read(url);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", baos);
		Image iTextImage = Image.getInstance(baos.toByteArray());
		
		return iTextImage;
	}
}