/* Created by James DSouza: Date June 27 2019*/
/* Change History
1. created initial file on June 27 2019 - jamesdsouza
******************************************************

*/
package konkani;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.net.MalformedURLException; 
import java.net.URL; 

	class Dictionary
	{  
		public static Document doc;
		public static String sdoc;
		public static List<String> lstNouns;
		
		public static Element dictionary ;
		public static Element alphabet ;
		public static Element sdefs ;	
		public static Element section ;
					
		public static Element sdefNoun ;
		public static Element sdefSingular ;
		public static Element sdefPlural ;
		
		public static Element e,p,l,r;
		
		public static void main(String args[]) throws ParserConfigurationException, ClassNotFoundException, Exception 
		{ 
			
			
			init();
			CreateInitialStructure();
			printXMLDocument(doc);
			GetNounsFromFile("KonkaniNounLexicon.txt");
			AddNounsToDictionary();
		}
		
		
		public static void init()
		throws ParserConfigurationException, ClassNotFoundException, Exception
		{
			System.out.println(" Initializing  ... ");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder        = factory.newDocumentBuilder();
			doc = builder.newDocument();
			lstNouns = new ArrayList<String> ();
		}
		
		public static Element createElement(String sNodeName)
		{
			//System.out.println("creating " + sNodeName);
			Element elem = doc.createElement(sNodeName); 
			return elem;
		}
		
		public static Element appendChild(Element parent, String sChild)
		{
			//System.out.println("adding child" + sChild);
			Element child = createElement(sChild); //Child Node
			parent.appendChild(child);
			return child;
		}
		
		public static Element createRootNode(String sRoot)
		{
			//System.out.println("creating root node " + sRoot);
			Element root  = createElement(sRoot); //root node
			doc.appendChild(root);
			return root;
		}
		
		public static void CreateInitialStructure()
		{
			   System.out.println("Creating Initial Structure");
			   try
			   {
					//root node
					 dictionary = createRootNode("dictionary");
					 alphabet = appendChild(dictionary,"alphabet");
					 sdefs = appendChild(dictionary,"sdefs");
					 section = appendChild(dictionary,"section");
					
					 sdefNoun = appendChild(sdefs,"sdef");
					 sdefSingular = appendChild(sdefs,"sdef");
					 sdefPlural = appendChild(sdefs,"sdef");
					

			   }
			   catch (Exception e)
			   {
				   System.out.println(e.toString());
				   throw e;
			   }
			
		}
		
		
		public static String printXMLDocument(Document doc) throws ParserConfigurationException, ClassNotFoundException, Exception
		{
			System.out.println("printing Document");
			
			DOMSource domSource = new DOMSource(doc);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);

			System.out.println(sw.toString());
			return sw.toString();
		}
		
		public static void GetNounsFromFile(String sFile)
		 throws FileNotFoundException,IOException
		{
			
			  System.out.println("reading Nouns from the File");
			  URL path = Dictionary.class.getResource(sFile);
			  File file = new File(path.getFile()); 
			  
			  BufferedReader br = new BufferedReader(new FileReader(file)); 
			  
			  String st; 
			  while ((st = br.readLine()) != null) 
			  {
				//System.out.println(st); 
				lstNouns.add(st);
			  } 	
			
		}
		
		
		public static Element addNounToDictionary(String sNoun)
		{
			Element e = createNodeForWord(sNoun);
			return e;
		}
		
		public static void AddNounsToDictionary()
		{
			int totalWords = lstNouns.size();
			String sNoun = "";
			
			System.out.println("Total words found in file = " + totalWords);
			for (int i = 0; i < totalWords; i++) 
			{
				sNoun = lstNouns.get(i);
				e = addNounToDictionary(sNoun);
			}
		}
		
		public static Element createNodeForWord(String sWord)
		{
			
			System.out.println("adding word = " + sWord );
			e = appendChild(section, "e");
			p = appendChild(e,"p");
			l = appendChild(p,"l");
			
			r = appendChild(p, "r");
			
			//add noun temporarily over here
			Text text = doc.createTextNode(sWord);
			l.appendChild(text);
			
			return e;
		
			
		}
		
		
		
		/*public static String printHTMLDocument(Document doc) throws ParserConfigurationException, ClassNotFoundException, Exception
		{
			String htmloutput = "";
			
			//start with the root node processing
			htmloutput = doSomethingOnXMLNode(doc.getDocumentElement(), htmloutput);
			
			return htmloutput;
		}
		
		public static String doSomethingOnXMLNode(Node node, String htmloutput) 
		{
			
			htmloutput = htmloutput + "<br>" + node.getNodeName() + "=" + node.getTextContent() ;
			
			
			// do something with the current node instead of System.out
			System.out.println(node.getNodeName() + "=" + node.getTextContent() );

			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node currentNode = nodeList.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					//calls this method for all the children which is Element
					htmloutput = doSomethingOnXMLNode(currentNode, htmloutput);
				}
			}
			
			return htmloutput;
		}*/
		
		
	}  