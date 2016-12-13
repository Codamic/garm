(ns garm.views.dashboard
  (:require [re-frame.core      :as re-frame]
            [garm.views.grommet :refer [app]]
            [garm.views.navbar  :refer [navbar]]
            [garm.views.sidebar :refer [sidebar]]))

(defn- wrapper-classes
  [sidebar-status]
  (if (true? sidebar-status) "enlarged forced" "expanded forced"))

(defn dashboard []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    (fn []
      [app {:lang "fa" :class  "rtl" :centered false}

       [split {:priority "right"
               :flex "right"
               :fixed false}
        [box {:justify "center"
              :align "center"
              :pad "none"}]




        [box {:pad "none"}
         [nav-bar {:toggle (this.toggleDrawer)}]]
        [: br]]

          <App lang={"fa"} className={'rtl'} centered={true}>

            <Box colorIndex="light-1">

              <Table scrollable={true}
                     selectable={true}>
                <thead>
                  <tr>
                    <th>
                      Name
                    </th>
                    <th>
                      Note
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <TableRow>
                    <td>
                      Alan
                    </td>
                    <td>
                      plays accordion
                    </td>
                  </TableRow>
                  <TableRow>
                    <td>
                      Tracy
                    </td>
                    <td>
                      travels the world
                    </td>
                  </TableRow>
                  <TableRow>
                    <td>
                      Chris
                    </td>
                    <td>
                      drops the mic
                    </td>
                  </TableRow>
                </tbody>

              </Table>

            </Box>
          </App>





        </Box>
      </Split>]













      [:div {:id "wrapper" :class (wrapper-classes  @sidebar-expanded)}
       [navbar]
       [sidebar]])))
