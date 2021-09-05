**Programming fundaments thats been absorbed in the Countries App:**

                a. Code Clarity
                b. Follow OOPs fundamentals
                c. Code commenting
                d. Code maintainability
                e. Proper naming conventions




**Base Architecture** : MVVM with Live Data and Data Binding.

**Android Components Used :**

                a. **MVVM based architecture** : Refer below for the files - CountriesViewModel.kt

                d. **LiveData** : 
                        // val userMutableLiveData: MutableLiveData<ArrayList<Country>?> get() = countriesLiveData

                         /**
                         * Observe Live Data
                         * Note : Live Data being one of the most preferred way of storing the data in the app,
                         * It can be scaled further to add new functionalities.
                         */**
                        fun observeData(){
                            viewModel?.userMutableLiveData?.observe(this, Observer{
                                recyclerViewAdapter = CountriesAdapter(mCountriesJson!!.countries)
                                mBindedView.rvCountries.setLayoutManager(LinearLayoutManager(applicationContext))
                                mBindedView.rvCountries.setAdapter(recyclerViewAdapter)
                            })
                        }

                c. **View Binding & Data Binding :** build.gradle

                    buildFeatures {
                        dataBinding true
                         viewBinding true
                    }

                d. **Layouts Used**: 
                  ConstraintLayout
                  LinearLayout 

                e. **Views Used**: 
                  CardView
                  RecyclerView
                  TextView





1. **AppName** : Countries
                <string name="app_name">Countries</string>
    
    
2. **AndroidManifest.xml**

                        <activity android:name=".ui.main.CountriesListActivity"
                            tools:ignore="LockedOrientationActivity">
                            <intent-filter>
                                <action android:name="android.intent.action.MAIN" />

                                <category android:name="android.intent.category.LAUNCHER" />
                            </intent-filter>
                        </activity>

    
    
3. **Json File in assets : country.json**

                     {

                      "countries" : [
                        {
                          "name": "India",
                          "description": "You stand on Indian soil."

                        },
                        {
                          "name": "USA",
                          "description": "You are in North America."
                        },
                        {
                          "name": "Australia",
                          "description": "Welcome to mainland Australia."
                        },
                        {
                          "name": "Egypt",
                          "description": "I hope you have seen the Pyramids."
                        },
                        {
                          "name": "England",
                          "description": "India shall beat England in the test series."
                        }
                      ]
                    }

    
4. **Use this json File from a Utility class file  - JsonUtil.kt**

                    /**
                     * The function is used to Read the Json from the embedded Json File.
                     * @param context - Activity Context
                     * @param fileName - a json file from the assets folder.
                     */
                    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
                        val jsonString: String
                        try {
                            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
                        } catch (ioException: IOException) {
                            ioException.printStackTrace()
                            return null
                        }
                        return jsonString
                    }

5. **Main Launcher Activity : CountriesListActivity.kt**

                     Used to bind the recycler View and set the data in the recycler View.

                     /**
                     * Member Variables of the class
                     *********** START ******************
                     */
                    private lateinit var mBindedView: ActivityCountriesBinding
                    private var mCountriesJson: Countries? = null
                    private var viewModel : CountriesViewModel ?= null
                    var recyclerViewAdapter: CountriesAdapter? = null
                    /**
                     * Member Variables of the class
                     *********** END ******************
                     */


                        override fun onCreate(savedInstanceState: Bundle?) {
                            super.onCreate(savedInstanceState)
                            //View Binding
                            mBindedView = ActivityCountriesBinding.inflate(layoutInflater)
                            val view = mBindedView.root
                            setContentView(view)
                            //Load the json from the json file placed in the Assets folder.
                            val jsonFileString = JsonUtil.getJsonDataFromAsset(applicationContext, "country.json")
                            val gson = Gson()
                            //Treating this Will be the Json Response
                            mCountriesJson = gson.fromJson(jsonFileString, Countries::class.java)
                            //Add a Countries Factory for getting the instance of the current ViewModel
                            val factory = CountriesViewModelFactory()
                            viewModel = ViewModelProviders.of(this,factory).get(CountriesViewModel::class.java)
                            observeData()
                        }


                        /**
                         * Observe Live Data
                         * Note : Live Data being one of the most preferred way of storing the data in the app,
                         * It can be scaled further to add new functionalities.
                         */
                        fun observeData(){
                            viewModel?.userMutableLiveData?.observe(this, Observer{
                                recyclerViewAdapter = CountriesAdapter(mCountriesJson!!.countries)
                                mBindedView.rvCountries.setLayoutManager(LinearLayoutManager(applicationContext))
                                mBindedView.rvCountries.setAdapter(recyclerViewAdapter)
                            })
                        }

6. **ViewModel : CountriesViewModel.kt**

                         class CountriesViewModel : ViewModel() {
                            var countriesLiveData: MutableLiveData<ArrayList<Country>?>
                            var countryArrayList: ArrayList<Country>? = null
                            val userMutableLiveData: MutableLiveData<ArrayList<Country>?> get() = countriesLiveData

                            fun init() {
                                countriesLiveData.value = countryArrayList
                            }


                            init {
                                countriesLiveData = MutableLiveData<ArrayList<Country>?>()
                                // Good to fire the network calls from init()
                                init()
                            }
                        }

  
7. **Base Factory for View Model : CountriesViewModelFactory.kt**
  
  
                        class CountriesViewModelFactory(): ViewModelProvider.Factory{
                            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                                if(modelClass.isAssignableFrom(CountriesViewModel::class.java)){
                                    return CountriesViewModel() as T
                                }
                                throw IllegalArgumentException ("UnknownViewModel")
                            }

                        }
  
  


App Supports Both Portrait and Landscape Modes :



![c91bcfe2-16ee-4cdd-b6fa-3eb810ebb4a6](https://user-images.githubusercontent.com/25679640/132139749-633f9f54-3a0f-4621-956a-d3336c03dca3.jpeg)


![1cae6f09-6d2a-4135-989a-1764057ee6f9](https://user-images.githubusercontent.com/25679640/132139764-8fca7e9a-e891-4d41-bc63-dec171ac3fb7.jpeg)






