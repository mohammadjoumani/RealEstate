package com.example.realestate.repository;import android.app.Application;import android.os.AsyncTask;import androidx.lifecycle.LiveData;import androidx.lifecycle.MutableLiveData;import com.example.realestate.model.dao.EstateDao;import com.example.realestate.model.database.RealEstateDateBase;import com.example.realestate.model.entity.Estate;import com.example.realestate.model.entity.EstateInfo;import java.util.List;public class EstateRepository {    private EstateDao estateDao;    private static MutableLiveData<Integer> estateID = new MutableLiveData<>();    public EstateRepository(Application application) {        estateDao = RealEstateDateBase.getDatabase( application ).getEstateDao();    }    public void insert(Estate estate) {        new InsertTask( estateDao ).execute( estate );    }    public void delete(Estate estate) {        new DeleteTask( estateDao ).execute( estate );    }    public void update(Estate estate) {        new UpateTask( estateDao ).execute( estate );    }    public void deleteByID(int ID) {        new DeleteByIDTask( estateDao ).execute( ID );    }    public LiveData<List<Estate>> getAllByEstateTypeID(int TypeEstateID, String OprationName) {        return estateDao.getAllByEstateTypeID( TypeEstateID, OprationName );    }    public LiveData<List<EstateInfo>> getEsateInfo(String oprationName, int typeEstateID) {        return estateDao.getEsateInfo( oprationName, typeEstateID );    }    public LiveData<List<EstateInfo>> getMyEstate(int officeID) {        return estateDao.getMyEstate( officeID );    }    public LiveData<Estate> getEstateByID(int id) {        return estateDao.getEstateByID( id );    }    public LiveData<Integer> getEstateID() {        return estateID;    }    private static class InsertTask extends AsyncTask<Estate, Void, Void> {        private EstateDao estateDao;        public InsertTask(EstateDao estateDao) {            this.estateDao = estateDao;        }        @Override        protected Void doInBackground(Estate... estates) {            estateID.postValue( 0 );            estateDao.insert( estates[0] );            estateID.postValue( estateDao.getMaxID() );            return null;        }    }    private static class DeleteTask extends AsyncTask<Estate, Void, Void> {        private EstateDao estateDao;        public DeleteTask(EstateDao estateDao) {            this.estateDao = estateDao;        }        @Override        protected Void doInBackground(Estate... estates) {            estateDao.delete( estates[0] );            return null;        }    }    private static class DeleteByIDTask extends AsyncTask<Integer, Void, Void> {        private EstateDao estateDao;        public DeleteByIDTask(EstateDao estateDao) {            this.estateDao = estateDao;        }        @Override        protected Void doInBackground(Integer... integers) {            estateDao.deleteByID( integers[0] );            return null;        }    }    private static class UpateTask extends AsyncTask<Estate, Void, Void> {        private EstateDao estateDao;        public UpateTask(EstateDao estateDao) {            this.estateDao = estateDao;        }        @Override        protected Void doInBackground(Estate... estates) {            estateDao.update( estates[0] );            return null;        }    }}