package com.edu.thss.smartdental.ui.dialog;

import com.edu.thss.smartdental.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JoinCircleDialog extends DialogFragment {
	
	private View dialogView;
	private String docName;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		dialogView = inflater.inflate(R.layout.dialog_join_circle, null);
		
		builder.setView(dialogView);
		builder.setTitle(R.string.enter_circle_password)
          .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
        	  	public void onClick(DialogInterface dialog, int id) {
        	  		EditText text = (EditText) dialogView.findViewById(R.id.circle_password);
        	  		String password = text.getText().toString();
        			
        	  		Toast.makeText(getActivity(), docName + " " + password, Toast.LENGTH_LONG).show();
        	  					}
          					})
          .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        	  	public void onClick(DialogInterface dialog, int id) {
        	  					}
               				});
		return builder.create();
    }
	
	public void setDocName(String name) {
		docName = name;
	}
}