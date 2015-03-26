package com.test.movideo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ResultActivity extends Activity {

	public String listAPIURL = "";
	
	public final static String LIST_VALUE = "LISTVALUE";
	public final static String KEY_WORD = "&keywords=['";
	ProgressDialog progressDialog;


	ArrayList<XmlValuesModel> listOfMovideo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Intent intent = getIntent();
		String result = intent.getStringExtra(Constants.EXTRA_MESSAGE);
		listAPIURL = Constants.LIST_ITEM_ONE + result + Constants.LIST_ITEM_CONTINUE;

		Bundle b = getIntent().getExtras();
		String divActivity;
		if (b != null) {
			String searchVal = b.getString("Search");
			if (searchVal != null && searchVal.trim().length() > 0) {
				divActivity = b.getString("Search");
				String searchLink = listAPIURL + KEY_WORD + divActivity + "']";
				submitInitialize(searchLink);
			} else {
				submitInitialize(listAPIURL);
			}
		} else {
			submitInitialize(listAPIURL);
		}
		/** Create a new layout to display the view */
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(1);

	}

	private void submitInitialize(String searchstring) {
		progressDialog = ProgressDialog.show(this, Constants.LOADING, Constants.PLEAS_WAIT, true);
		
		new CallList().execute(searchstring);
	}

	private class CallList extends AsyncTask<String, String, List<XmlValuesModel>> {

		XmlValuesModel movideoValues = null;

		@Override
		protected List<XmlValuesModel> doInBackground(String... params) {

			String paramURL = params[0];

			try {
				Document docs = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramURL);

				NodeList entries = docs.getElementsByTagName("media");
				listOfMovideo = new ArrayList<XmlValuesModel>();
				for (int i = 0; i < entries.getLength(); i++) {

					NodeList children = entries.item(i).getChildNodes();

					movideoValues = new XmlValuesModel();
					for (int j = 0; j < children.getLength(); j++) {
						String text = "";
						Node child = children.item(j);
						if (child.getNodeName().equals("#text")) {

						}
						// Reading additionalImages
						else if (child.getNodeName().equals("additionalImages")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {

								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}

							movideoValues.setAdditionalImages(text);

						}
						// Reading client
						else if (child.getNodeName().equals("client")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0) {
								for (int k = 0; k < smallChild.getLength(); k++) {
									Node clientchild = smallChild.item(k);
									if (clientchild.getNodeName().equals("#text")) {

									}
									// Reading Client ID
									else if (clientchild.getNodeName().equals("id")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "0";
										}

										movideoValues.setClientid(Integer.parseInt(text));

									}
									// Reading Client Alias
									else if (clientchild.getNodeName().equals("alias")) {

										NodeList smallClientChild = clientchild.getChildNodes();
										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "";
										}

										movideoValues.setClientalias(text);

									}
								}// end for loop
							}

						}

						// Reading copyright
						else if (child.getNodeName().equals("copyright")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setCopyright(text);

						}
						// Reading creator
						else if (child.getNodeName().equals("creator")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setCreator(text);

						}

						// Reading creationDate
						else if (child.getNodeName().equals("creationDate")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setCreationDate(text);

						}
						// Reading cuePointsExist
						else if (child.getNodeName().equals("cuePointsExist")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "false";
							}
							movideoValues.setCuePointsExist(Boolean.valueOf(text));

						}
						// Reading description
						else if (child.getNodeName().equals("description")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setDescription(text);

						}
						// Reading defaultImage
						else if (child.getNodeName().equals("defaultImage")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0) {
								for (int k = 0; k < smallChild.getLength(); k++) {
									Node clientchild = smallChild.item(k);
									if (clientchild.getNodeName().equals("#text")) {

									}
									// Reading url
									else if (clientchild.getNodeName().equals("url")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "";
										}
										movideoValues.setDefaultImageurl(text);

									}
									// Reading height
									else if (clientchild.getNodeName().equals("height")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "0";
										}
										movideoValues.setDefaultImageheight(Integer.parseInt(text));

									}
									// Reading width
									else if (clientchild.getNodeName().equals("width")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "0";
										}
										movideoValues.setDefaultImagewidth(Integer.parseInt(text));

									}

								}// end for loop
							}

						}
						// Reading duration
						else if (child.getNodeName().equals("duration")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "0";
							}
							movideoValues.setDuration(text);

						}
						// Reading externalAuthentication
						else if (child.getNodeName().equals("externalAuthentication")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "false";
							}
							movideoValues.setExternalAuthentication(Boolean.valueOf(text));

						}
						// Reading File name
						else if (child.getNodeName().equals("filename")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setFilename(text);

						}
						// Reading ID
						else if (child.getNodeName().equals("id")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "0";
							}

							movideoValues.setId(Integer.parseInt(text));

						}
						// Reading imageFilename
						else if (child.getNodeName().equals("imageFilename")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setImageFilename(text);

						}

						// Reading imagePath
						else if (child.getNodeName().equals("imagePath")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setImagePath(text);

						}

						// Reading isAdvertisement
						else if (child.getNodeName().equals("isAdvertisement")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "false";
							}
							movideoValues.setAdvertisement(Boolean.valueOf(text));

						}

						// Reading lastModifiedDate
						else if (child.getNodeName().equals("lastModifiedDate")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setLastModifiedDate(text);

						}

						// Reading mediaFileExists
						else if (child.getNodeName().equals("mediaFileExists")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "false";
							}
							movideoValues.setMediaFileExists(Boolean.valueOf(text));

						}

						// Reading mediaPlays
						else if (child.getNodeName().equals("mediaPlays")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0) {
								for (int k = 0; k < smallChild.getLength(); k++) {
									Node clientchild = smallChild.item(k);
									if (clientchild.getNodeName().equals("#text")) {

									}
									// Reading day
									else if (clientchild.getNodeName().equals("day")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "0";
										}

										int mediaPlays = Integer.parseInt(text);
										movideoValues.setMediaPlaysday(mediaPlays);

									}
									// Reading month
									else if (clientchild.getNodeName().equals("month")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "0";
										}
										int mediaPlays = Integer.parseInt(text);
										movideoValues.setMediaPlaysmonth(mediaPlays);

									}
									// Reading week
									else if (clientchild.getNodeName().equals("week")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "0";
										}
										int mediaPlays = Integer.parseInt(text);
										movideoValues.setMediaPlaysweek(mediaPlays);

									}
									// Reading total
									else if (clientchild.getNodeName().equals("total")) {

										NodeList smallClientChild = clientchild.getChildNodes();

										if (smallClientChild != null && smallClientChild.getLength() > 0
												&& smallClientChild.item(0).getNodeValue() != null) {
											text = smallClientChild.item(0).getNodeValue();
										} else {
											text = "0";
										}
										int mediaPlays = Integer.parseInt(text);
										movideoValues.setMediaPlaystotal(mediaPlays);

									}

								}// end for loop
							}

						}
						// Reading mediaType
						else if (child.getNodeName().equals("mediaType")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setMediaType(text);

						}

						// Reading payWalls
						else if (child.getNodeName().equals("payWalls")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setPayWalls(text);

						}
						// Reading ratio
						else if (child.getNodeName().equals("ratio")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setRatio(text);

						}
						// Reading status
						else if (child.getNodeName().equals("status")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setStatus(text);

						}
						// Reading syndicated
						else if (child.getNodeName().equals("syndicated")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "false";
							}
							movideoValues.setSyndicated(Boolean.valueOf(text));

						}
						// Reading tags
						else if (child.getNodeName().equals("tags")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setTags(text);

						}
						// Reading title
						else if (child.getNodeName().equals("title")) {

							NodeList smallChild = child.getChildNodes();
							if (smallChild != null && smallChild.getLength() > 0
									&& smallChild.item(0).getNodeValue() != null) {
								text = smallChild.item(0).getNodeValue();
							} else {
								text = "";
							}
							movideoValues.setTitle(text);

						}

					}
					listOfMovideo.add(movideoValues);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return listOfMovideo;
		}

		/** Set the layout view to display */

		protected void onPostExecute(List<XmlValuesModel> result) {

			ArrayList<XmlValuesModel> arrayList = new ArrayList<XmlValuesModel>();

			arrayList = listOfMovideo;
			Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
			intent.putExtra("FILES_TO_SEND", arrayList);
			startActivity(intent);
			progressDialog.cancel();
		}

	}// end CallList

}